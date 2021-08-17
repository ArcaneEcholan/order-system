package cn.edu.bistu.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class Flow {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(fill= FieldFill.INSERT)
    private Date createTime;
    @TableField(fill= FieldFill.UPDATE)
    private Date updateTime;
    private String description;
    private Integer auth_id;
    private String name;

    @TableLogic
    private Integer deleted;
}
