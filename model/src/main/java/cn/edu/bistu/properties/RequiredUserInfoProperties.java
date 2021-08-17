package cn.edu.bistu.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@Component
@PropertySource("classpath:requiredUserInfo.properties")
@ConfigurationProperties(prefix = "required-user-info")
public class RequiredUserInfoProperties {

    List<String> requiredStudentUserInfo = new ArrayList<>();

    List<String> requiredTeacherUserInfo = new ArrayList<>();
}
