package cn.edu.bistu.common.config;

import cn.edu.bistu.properties.RequiredUserInfoProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class RequiredUserInfoPropertiesTest {

    @Autowired
    RequiredUserInfoProperties properties;

    @Test
    public void loadProperties() {
        List<String> requiredStudentUserInfo = properties.getRequiredStudentUserInfo();
        System.out.println(requiredStudentUserInfo);
        List<String> requiredTeacherUserInfo = properties.getRequiredTeacherUserInfo();
        System.out.println(requiredTeacherUserInfo);
    }


}