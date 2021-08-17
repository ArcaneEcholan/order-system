package cn.edu.bistu.auth.model;

import cn.edu.bistu.constants.Role;
import cn.edu.bistu.model.entity.User;
import cn.edu.bistu.model.UserInfoChecker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserInfoCheckerTest {

    @Autowired
    UserInfoChecker userInfoChecker;

    @Test
    public void checkFrontUserInfo() {
        User user = new User();
        user.setRole(Role.UNDERGRADUATE);
        user.setStudentId(2382734l);
        user.setClassId(1);
        user.setGrade(2);
        user.setMajorId(2);
        user.setName("wc");
        user.setCollegeId(1);

        userInfoChecker.setUser(user);

        boolean b = false;
        try {
            b = userInfoChecker.checkFrontUserInfo();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(b);

    }
}
