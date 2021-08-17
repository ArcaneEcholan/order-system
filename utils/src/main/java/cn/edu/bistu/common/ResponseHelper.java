package cn.edu.bistu.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseHelper {
    public static void returnJson(HttpServletResponse resp, JSONObject obj) throws IOException {
        resp.setContentType("application/json");
        String s = obj.toJSONString();
        resp.getWriter().write(s);
    }


    public static void returnJson(HttpServletResponse resp, String obj) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(obj);
    }

    public static void returnJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType("application/json");
        String s = JSON.toJSONString(obj);
        returnJson(resp, s);
    }
}
