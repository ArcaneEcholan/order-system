package cn.edu.bistu.workOrder.mapper;

import cn.edu.bistu.model.entity.WorkOrder;
import cn.edu.bistu.model.vo.WorkOrderVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkOrderMapper extends BaseMapper<WorkOrder>{

    /**
     * 返回workOrderVo的page
     * @return
     */
    Page<WorkOrderVo> workOrderPages(Page<WorkOrder> page, WorkOrderVo workOrderVo);


}
