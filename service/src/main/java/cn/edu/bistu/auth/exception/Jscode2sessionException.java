package cn.edu.bistu.auth.exception;

import lombok.Data;
import sun.security.util.math.ImmutableIntegerModuloP;

@Data
public class Jscode2sessionException extends RuntimeException{

    private Integer errcode;
    private String errmsg;

    public Jscode2sessionException(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }
}
