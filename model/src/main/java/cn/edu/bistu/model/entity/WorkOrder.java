package cn.edu.bistu.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
public class WorkOrder {
    @TableId(type=IdType.AUTO)
    private Long id;
    private Integer isExamined;
    private Long initiatorId;
    private Long flowId;

    private String title;

    @TableField(fill= FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(fill= FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private byte[] attachment;
    private String attachmentName;

    private Integer status;
    private String content;

    @TableLogic
    private Integer deleted;
}
