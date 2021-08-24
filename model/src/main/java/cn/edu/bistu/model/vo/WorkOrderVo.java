package cn.edu.bistu.model.vo;

import cn.edu.bistu.model.entity.WorkOrder;
import lombok.Data;



@Data
public class WorkOrderVo extends WorkOrder{

    String studentId;
    String jobId;
    String initiatorName;
    String flowName;

    String attachmentUrl;

    Long size;
    Long current;
}
