package cn.edu.bistu.common.interceptor;

import cn.edu.bistu.auth.JwtHelper;
import cn.edu.bistu.common.MapService;
import cn.edu.bistu.common.ResponseHelper;
import cn.edu.bistu.constants.ResultCodeEnum;
import cn.edu.bistu.model.common.Result;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class TokenAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取token
        String token=request.getHeader("token");

        if(token == null) {
            ResponseHelper.returnJson(response, Result.build(null, ResultCodeEnum.SIGN_MISSING));
            log.debug("====================SIGN_MISSING=====================");
            return false;
        }

        //验证token
        try {
            Jws<Claims> claimsJws = JwtHelper.verifySignature(token);
        } catch (SignatureException ex) {
            //签名错误
            ResponseHelper.returnJson(response, Result.build(null, ResultCodeEnum.SIGN_ERROR));
            return false;
        } catch (ExpiredJwtException ex) {
            //token失效
            ResponseHelper.returnJson(response, Result.build(null, ResultCodeEnum.TOKEN_EXPIRED));
            return false;
        }

        //转发或放行
        Long id = JwtHelper.getClaim(token, "id", Integer.class).longValue();

        Long studentId = JwtHelper.getClaim(token, "studentId", Long.class);

        Long jobId = JwtHelper.getClaim(token, "jobId", Long.class);

        log.info("id=" + id);
        log.info("studentId=" + studentId);
        log.info("jobId=" + jobId);

        MapService mapService = MapService.map()
                .putMap("id", id)
                .putMap("studentId", studentId)
                .putMap("jobId", jobId);
        request.setAttribute("userInfo", mapService);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
