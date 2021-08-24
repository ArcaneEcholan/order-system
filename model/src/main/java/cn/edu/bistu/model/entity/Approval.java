package cn.edu.bistu.model.entity;


import lombok.Data;

import java.util.Date;

/**
 * 工单与审批节点之间的关系实体，用于记录工单在流程中的节点位置
 */
@Data
public class Approval extends BaseEntity
{

    /**
     * 审批操作（无论是通过还是不通过）的时间戳
     */
    private Date approvalDatetime;

    /**
     * 审批留言
     */
    private String comment;

    /**
     * 审批对应的工单id
     */
    private Long workOrderId;

    /**
     * 审批对应的节点id
     */
    private Long flowNodeId;
}
