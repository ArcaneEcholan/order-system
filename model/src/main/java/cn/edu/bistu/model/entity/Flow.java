package cn.edu.bistu.model.entity;

import lombok.Data;

@Data
public class Flow extends BaseEntity{
    private String description;
    private Integer role_id;
    private String name;
}
