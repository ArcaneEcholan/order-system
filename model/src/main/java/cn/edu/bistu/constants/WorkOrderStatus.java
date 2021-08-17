package cn.edu.bistu.constants;

public enum WorkOrderStatus {
    /**
     * 在审批
     */
    BEING_EXAMINED,

    /**
     * 顺利通过
     */
    COMPLETED_SUCCESSFULLY,

    /**
     * 审批不通过
     */
    NOT_APPROVED,

    /**
     * 被撤回
     */
    BEEN_WITHDRAWN

}
