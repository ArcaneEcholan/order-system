package cn.edu.bistu.auth.service.impl;

import cn.edu.bistu.auth.JwtHelper;
import cn.edu.bistu.auth.exception.Jscode2sessionException;
import cn.edu.bistu.auth.service.AuthService;
import cn.edu.bistu.auth.service.UserService;
import cn.edu.bistu.common.BeanUtils;
import cn.edu.bistu.constants.ResultCodeEnum;
import cn.edu.bistu.model.common.Result;
import cn.edu.bistu.model.WxLoginStatus;
import cn.edu.bistu.model.entity.User;
import cn.edu.bistu.model.vo.UserVo;
import cn.edu.bistu.weixin.WxMiniApi;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    WxMiniApi wxMiniApi;

    @Autowired
    UserService userService;

    @Value("${appId}")
    String appId;

    @Value("${appSecret}")
    String appSecret;

    private WxLoginStatus getWxLoginStatus(String code) throws Jscode2sessionException{
        JSONObject jsonObject = wxMiniApi.authCode2Session(appId, appSecret, code);
        if (jsonObject == null) {
            throw new RuntimeException("调用微信端授权认证接口错误");
        } else if(jsonObject.get("errcode") != null) {
            throw new Jscode2sessionException((Integer) jsonObject.get("errcode")
                    , (String)jsonObject.get("errmsg"));
        }

        String openId = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        WxLoginStatus wxLoginStatus = new WxLoginStatus();
        wxLoginStatus.setOpenId(openId);
        wxLoginStatus.setSessionKey(sessionKey);
        return wxLoginStatus;
    }

    @Override
    public Result login(String code) {

        String openId = "";
        String sessionKey = "";

        try {
            //获取微信登录态
            WxLoginStatus wxLoginStatus = getWxLoginStatus(code);
            openId = wxLoginStatus.getOpenId();
            sessionKey = wxLoginStatus.getSessionKey();
        }catch (Jscode2sessionException ex) {
            if(ex.getErrcode().equals(40029)) {
                return new Result().codeEnum(ResultCodeEnum.OAUTH_CODE_INVALID);
            } else if(ex.getErrcode().equals(40163)) {
                return new Result().codeEnum(ResultCodeEnum.OAUTH_CODE_BEEN_USED);
            }
        }

        //判断用户表中是否存在该用户，不存在则进行解密得到用户信息，并进行新增用户
        User resultUser = userService.findByOpenId(openId);

        ResultCodeEnum resultCode = null;
        Map<String, Object> data = new HashMap<>();

        //用户没有注册，向数据库插入新用户，不返回token
        if (resultUser == null) {

            resultUser = new User();
            resultUser.setOpenId(openId);
            resultUser.setSessionKey(sessionKey);
            resultUser.setInfoComplete(0);
            userService.save(resultUser);

            data.put("user", resultUser);
            data.put("token", null);

            resultCode = ResultCodeEnum.USER_INFO_NOT_COMPLETE;
        }
        //用户已经注册，判断是否完善了信息，是则返回token
        else {
            data.put("user", resultUser);

            //如果用户已经完善信息，返回登录token
            if(resultUser.getInfoComplete() == 1) {
                Map<String, Object> claim = new HashMap<>();
                claim.put("id", resultUser.getId());
                claim.put("studentId", resultUser.getStudentId());
                claim.put("jobId", resultUser.getJobId());
                String token = JwtHelper.createToken(claim);

                data.put("token", token);
                resultCode = ResultCodeEnum.SUCCESS;
            }
            //如果用户没有完善信息，就不返回登录token
            else {
                resultCode = ResultCodeEnum.USER_INFO_NOT_COMPLETE;
            }
        }

        Map<String, Object> resultData = BeanUtils.bean2Map(data.get("user"), new String[]{
                "openId",
                "sessionKey"
        });


        return Result.build(resultData, resultCode);
    }
}
