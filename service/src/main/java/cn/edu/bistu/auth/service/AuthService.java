package cn.edu.bistu.auth.service;

import cn.edu.bistu.model.common.Result;

public interface AuthService{
    /**
     * 用户登录
     * @param code
     * @return
     */
    Result login(String code);
}
