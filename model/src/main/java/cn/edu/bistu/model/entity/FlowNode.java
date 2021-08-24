package cn.edu.bistu.model.entity;

import lombok.Data;

@Data
public class FlowNode extends BaseEntity {

    /**
     * 节点对应的流程Id
     */
    private Long flowId;

    /**
     * 节点对应的审批者Id
     */
    private Long approverId;

    /**
     * 节点的顺序（从0开始）
     */
    private Long nodeOrder;

    /**
     * 下一个节点的Id
     */
    private Long nextId;
}
