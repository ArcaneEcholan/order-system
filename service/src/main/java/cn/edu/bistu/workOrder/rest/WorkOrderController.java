package cn.edu.bistu.workOrder.rest;

import cn.edu.bistu.auth.JwtHelper;
import cn.edu.bistu.common.BeanUtils;
import cn.edu.bistu.common.MapService;
import cn.edu.bistu.model.common.Result;
import cn.edu.bistu.model.vo.WorkOrderVo;
import cn.edu.bistu.workOrder.service.WorkOrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Slf4j
@RestController
@CrossOrigin
public class WorkOrderController {

    @Autowired
    WorkOrderService workOrderService;

    /**
     * 返回分页的工单列表，支持名称模糊搜索
     * 入参：size(10)，current(1)，title(NULL)
     * @return
     */
    @PostMapping("/workOrder/list")
    public Result list(@RequestBody WorkOrderVo workOrderVo, HttpServletRequest req) {
        MapService userInfo = (MapService)req.getAttribute("userInfo");
        Long id = userInfo.getVal("id", Long.class);
        workOrderVo.setInitiatorId(id);

        IPage<WorkOrderVo> result = workOrderService.listWorkOrder(workOrderVo);

        Map<String, Object> resultMap = BeanUtils.bean2Map(result,
                new String[]{
                        "serialVersionUID",
                        "hitCount",
                        "optimizeCountSql",
                        "orders",
                        "isSearchCount"
                });

        log.debug(((List<WorkOrderVo>)resultMap.get("records")).get(0).getCreateTime().toString());

        return Result.ok(resultMap);
    }

    /**
     * 根据工单号返回工单附件
     * 入参：工单号(路径传参)
     * @return
     */
    @PostMapping("/workOrder/attachment/{workOrderId}")
    public Result attachment(@PathVariable(name = "workOrderId") Integer workOrderId) {
        System.out.println(workOrderId);




        return Result.build(null);
    }


}
