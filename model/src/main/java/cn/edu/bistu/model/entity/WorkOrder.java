package cn.edu.bistu.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
public class WorkOrder extends BaseEntity{


    @TableField(fill= FieldFill.INSERT)
    private Integer isExamined;

    @NotNull
    private Long initiatorId;
    @NotNull
    private Long flowId;

    @NotNull
    private String title;

    private byte[] attachment;
    private String attachmentName;


    @TableField(fill= FieldFill.INSERT)
    private Integer status;


    @NotNull
    private String content;


}
