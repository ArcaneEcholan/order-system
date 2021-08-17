package cn.edu.bistu.model.entity;

import lombok.Data;

/**
 * 从数据库接收User类Id对应的名字
 */
@Data
public class NamesOfUserInfo {

    private String college;
    private String major;
    private String clazz;
    private String secondaryDept;

}
