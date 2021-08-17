package cn.edu.bistu.auth.service.impl;

import cn.edu.bistu.auth.mapper.UserMapper;
import cn.edu.bistu.auth.service.UserService;
import cn.edu.bistu.constants.ResultCodeEnum;
import cn.edu.bistu.model.common.Result;
import cn.edu.bistu.model.entity.User;
import cn.edu.bistu.model.UserInfoChecker;
import cn.edu.bistu.model.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;


    @Autowired
    UserInfoChecker userInfoChecker;

    @Override
    public User findByOpenId(String openId) {
        UserVo userVo = userMapper.selectByOpenId(openId);
        return userVo;
    }

    @Override
    public Result userInfoCompletion(User user) {

        //检查Id是否传递
        if(user.getId() == null) {
            return new Result().codeEnum(ResultCodeEnum.FRONT_DATA_MISSING);
        }

        //检查用户是否注册
        User user1 = userMapper.selectById(user.getId());
        if(user1 == null) {
            return new Result().codeEnum(ResultCodeEnum.USER_UNREGISTERED);
        }

        //检查用户信息是否完善，已经完善就跳过
        Integer isComplete = userMapper.selectById(user.getId()).getInfoComplete();

        userInfoChecker.setUser(user);
        //如果未完善，检查前端信息是否完整
        if (isComplete.equals(0)) {
            boolean frontInfoComplete = false;

            try {
                frontInfoComplete = userInfoChecker.checkFrontUserInfo();
            } catch (NoSuchFieldException e) {
                log.error("requiredUserInfo.properties配置文件属性配置错误");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            //如果完整，更新表，否则返回错误代码
            if (frontInfoComplete) {
                user.setInfoComplete(1);
                updateById(user);
                return Result.ok();
            } else {
                return new Result().codeEnum(ResultCodeEnum.FRONT_DATA_MISSING);
            }
        } else {
            return Result.ok();
        }
    }




}
