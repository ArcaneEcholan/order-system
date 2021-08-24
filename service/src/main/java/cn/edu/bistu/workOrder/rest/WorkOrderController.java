package cn.edu.bistu.workOrder.rest;

import cn.edu.bistu.approval.service.ApprovalService;
import cn.edu.bistu.auth.exception.AttachmentNotExistsException;
import cn.edu.bistu.common.BeanUtils;
import cn.edu.bistu.common.MapService;
import cn.edu.bistu.common.config.ValidationWrapper;
import cn.edu.bistu.common.utils.MimeTypeUtils;
import cn.edu.bistu.constants.ResultCodeEnum;
import cn.edu.bistu.model.common.Result;
import cn.edu.bistu.model.entity.Approval;
import cn.edu.bistu.model.entity.WorkOrder;
import cn.edu.bistu.model.vo.WorkOrderVo;
import cn.edu.bistu.workOrder.service.WorkOrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@Slf4j
@RestController
@CrossOrigin
public class WorkOrderController {

    @Autowired
    ApprovalService approvalService;

    @Autowired
    WorkOrderService workOrderService;

    @Autowired
    ValidationWrapper globalValidator;

    /**
     * 返回分页的工单列表，支持名称模糊搜索
     * 入参：size(10)，current(1)，title(NULL)
     *
     * @return
     */
    @PostMapping("/workOrder/list")
    public Result list(@RequestBody WorkOrderVo workOrderVo, HttpServletRequest req) {
        MapService userInfo = (MapService) req.getAttribute("userInfo");
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

        log.debug(((List<WorkOrderVo>) resultMap.get("records")).get(0).getCreateTime().toString());


        return Result.ok(resultMap);
    }

    /**
     * 根据工单号返回工单附件
     * 入参：工单号(路径传参)
     *
     * @return
     */
    @GetMapping("/workOrder/attachment/{workOrderId}")
    public void downloadAttachment(@PathVariable(name = "workOrderId") Long workOrderId, HttpServletResponse resp) throws IOException {

        //查询附件
        WorkOrder workOrder = workOrderService.getById(workOrderId);
        byte[] attachmentBytes = workOrder.getAttachment();

        //log.debug("" + attachmentBytes.length);

        if (attachmentBytes == null) {
            throw new AttachmentNotExistsException();
        }


        //获取附件的MIME类型
        String mimeType = MimeTypeUtils.getType(workOrder.getAttachmentName());
        //设置响应的MIME类型
        resp.setContentType(mimeType);

        log.debug("mimeType:" + mimeType);

        //让浏览器以附件形式处理响应数据
        resp.setHeader("Content-Disposition", "downloadAttachment; fileName=" + URLEncoder.encode(workOrder.getAttachmentName(), "UTF-8"));

        log.debug("attachmentName:" + workOrder.getAttachmentName());

        //将二进制附件写入到http响应体中
        ServletOutputStream out = resp.getOutputStream();
        out.write(attachmentBytes, 0, attachmentBytes.length);
    }


    /**
     * 上传工单附件
     * 入参：附件、工单号
     *
     * @return 如果缺失上传文件，返回错误代码102
     */
    @PostMapping("/workOrder/attachment/{workOrderId}")
    public Result uploadAttachment(
            @RequestPart("attachment") MultipartFile attachment
            , @PathVariable(name = "workOrderId") Long workOrderId
    ) throws IOException {

        if (attachment.getSize() != 0 && !attachment.getOriginalFilename().equals("")) {
            byte[] bytes = attachment.getBytes();
            WorkOrder workOrder = new WorkOrder();
            workOrder.setId(workOrderId);
            workOrder.setAttachment(bytes);
            workOrder.setAttachmentName(attachment.getOriginalFilename());
            workOrderService.updateById(workOrder);
            return Result.ok();
        } else {
            return Result.build(null, ResultCodeEnum.FRONT_DATA_MISSING);
        }
    }

    /**
     * 提交工单接口，保存工单信息，同时工单被流转到第一个审批节点
     * @return
     */
    @PostMapping("/workOrder/submission")
    public Result submitWorkOrder(@RequestBody WorkOrderVo workOrderVo,
                                  HttpServletRequest req) {

        MapService userInfo = (MapService) req.getAttribute("userInfo");
        Long id = userInfo.getVal("id", Long.class);
        workOrderVo.setInitiatorId(id);

        try {
            globalValidator.setRequiredPropsName(new String[]{"initiatorId", "flowId", "content", "title"});

            Set<ConstraintViolation<WorkOrderVo>> set = globalValidator.validate(workOrderVo);

            if (!set.isEmpty()) {
                List<String> missingProps = new ArrayList<>();
                for (ConstraintViolation<WorkOrderVo> constraintViolation : set) {
                    String propName = constraintViolation.getPropertyPath().toString();
                    missingProps.add(propName);

                    log.debug(propName + ":" + constraintViolation.getMessage());
                }

                return Result.build(missingProps, ResultCodeEnum.FRONT_DATA_MISSING);
            }

            List<String> redundantParams = globalValidator.checkRedundantParam(workOrderVo);

            if (!redundantParams.isEmpty()) {
                return Result.build(redundantParams, ResultCodeEnum.FRONT_DATA_MISSING);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            globalValidator.setRequiredPropsNameNull();
        }

        workOrderService.save(workOrderVo);
        log.debug("workOrderVo id after saving:" + workOrderVo.getId());

        Approval approval = new Approval();

        approval.setFlowNodeId(0L);
        approval.setWorkOrderId(workOrderVo.getId());
        log.debug("approval flowNodeId:" + approval.getFlowNodeId());
        log.debug("approval workOrderId:" + approval.getWorkOrderId());

        approvalService.save(approval);

        return Result.ok();
    }

}
