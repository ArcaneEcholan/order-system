package cn.edu.bistu.model;

import lombok.Data;

@Data
public class WxLoginStatus {
    private String sessionKey;
    private String openId;
}
