package cn.edu.bistu.auth.service;

import cn.edu.bistu.model.common.Result;
import cn.edu.bistu.model.entity.User;
import cn.edu.bistu.model.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {


    /**
     * 根据openId查找用户
     * @param openId
     * @return 存在返回User对象，否则返回Null
     */
    UserVo findByOpenId(String openId);

    Result userInfoCompletion(UserVo userVo);

}
