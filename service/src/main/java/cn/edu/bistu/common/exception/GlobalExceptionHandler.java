package cn.edu.bistu.common.exception;

import cn.edu.bistu.auth.exception.AttachmentNotExistsException;
import cn.edu.bistu.constants.ResultCodeEnum;
import cn.edu.bistu.model.common.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 方法触发条件：如果请求下载工单的附件，但是工单没有附件
     * @return
     */
    @ExceptionHandler({AttachmentNotExistsException.class})
    @ResponseBody
    public Result attachmentNotExistsException(AttachmentNotExistsException ex){
        return Result.build(null, ResultCodeEnum.ATTACHMENT_NOT_EXISTS);
    }
}
