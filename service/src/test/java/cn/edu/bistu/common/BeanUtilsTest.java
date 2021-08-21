package cn.edu.bistu.common;

import cn.edu.bistu.model.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
//import cn.edu.bistu.common.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class BeanUtilsTest {


    @org.junit.Test
    public void formatDate() {
       User user = new User();
       user.setCreateTime(new Date());
        System.out.println(user.getCreateTime());
        Map<String, Object> map = BeanUtils.bean2Map(user, new String[]{
                "id"
        });
        Object createTime = map.get("createTime");
        System.out.println(createTime);
    }


    @org.junit.Test
    public void test() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(date);
        String format = dateFormat.format(date);
        System.out.println(format);

        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss.SSS 'GMT' Z");
        format = dateFormat.format(date);
        System.out.println(format);

        //Tue Aug 17 14:47:21 CST 2021
        //2021-08-17 14:47:21
        //2021-08-17 14:47:21.064 GMT +0800
    }


    @Test
    public void bean2Map() throws IllegalAccessException {

        Page<String> page = new Page<>();
        page.setSize(1);
        page.setCurrent(1);

        List<String> list = new ArrayList<>();
        list.add("hello");

        page.setRecords(null);

        Map<String, Object> stringObjectMap = BeanUtils.bean2Map(page);
        stringObjectMap.remove("orders");
        System.out.println(stringObjectMap);



    }
}
