package cn.edu.bistu.model;

import cn.edu.bistu.common.BeanUtils;
import cn.edu.bistu.model.entity.User;
import cn.edu.bistu.model.vo.UserVo;
import cn.edu.bistu.properties.RequiredUserInfoProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.lang.reflect.Field;
import java.util.List;

@Component
@Data
@Slf4j
public class UserInfoChecker {

    @Autowired
    private RequiredUserInfoProperties requiredUserInfoProperties;

    private UserVo user;

    public boolean checkFrontUserInfo() throws NoSuchFieldException, IllegalAccessException {
        String profile = checkAuth();

        List<Field> fieldList = BeanUtils.getAllDeclaredFields(UserVo.class);
        List<String> requiredUserInfo = null;

        if ("teacher".equals(profile)) {
            requiredUserInfo =
                    requiredUserInfoProperties.getRequiredTeacherUserInfo();
        } else if ("student".equals(profile)) {
            requiredUserInfo =
                    requiredUserInfoProperties.getRequiredStudentUserInfo();
        }

        if(requiredUserInfo != null) {
            for (String requiredProperty : requiredUserInfo) {

                Field declaredField;
                for (int i = 0; i < fieldList.size(); i++) {
                    declaredField = fieldList.get(i);
                    declaredField.setAccessible(true);
                    boolean equals = fieldList.get(i).getName().equals(requiredProperty);
                    if (equals) {
                        Object o = declaredField.get(user);
                        if (o == null) {
                            log.debug(declaredField.getName());
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public String checkAuth() {
        if (user.getRole() == null) {
            return null;
        }
        switch (user.getRole().getValue()) {
            case 2:
            case 3:
            case 4:
                return "teacher";
            case 5:
            case 6:
                return "student";
            default:
                return null;
        }
    }
}
