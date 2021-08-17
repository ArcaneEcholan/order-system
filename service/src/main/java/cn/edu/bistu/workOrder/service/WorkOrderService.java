package cn.edu.bistu.workOrder.service;

import cn.edu.bistu.model.entity.WorkOrder;
import cn.edu.bistu.model.vo.PageVo;
import cn.edu.bistu.model.vo.WorkOrderVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WorkOrderService extends IService<WorkOrder>{

    IPage<WorkOrderVo> listWorkOrder(WorkOrderVo workOrderVo);

}
