package cn.edu.bistu.constants;
import lombok.Getter;

/**
 * 统一返回结果状态信息类
 *
 * @author qy
 */
@Getter
public enum ResultCodeEnum {

    TOKEN_EXPIRED(100, "token已失效"),
    USER_INFO_NOT_COMPLETE(101, "用户信息未完善"),
    FRONT_DATA_MISSING(102, "前端数据缺失"),
    USER_UNREGISTERED(103, "此id用户未注册"),
    OAUTH_CODE_INVALID(104, "无效的 oauth_code"),
    OAUTH_CODE_BEEN_USED(105, "oauth_code 已被使用"),
    USER_INFO_COMPLETED(106, "用户信息无需完善"),
    ATTACHMENT_NOT_EXISTS(107, "附件不存在"),

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(202, "服务异常"),
    DATA_ERROR(204, "数据异常"),

    SIGN_ERROR(300, "签名错误"),
    SIGN_MISSING(301, "签名丢失"),

    PAY_PASSWORD_ERROR(401, "支付密码错误"),
    REPEAT_ERROR(402, "重复提交"),

    INVEST_AMMOUNT_MORE_ERROR(501, "出借金额已经多余标的金额"),
    RETURN_AMMOUNT_MORE_ERROR(502, "还款金额不正确"),
    PROJECT_AMMOUNT_ERROR(503, "标的金额不一致"),



    ;

    private Integer code;

    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
