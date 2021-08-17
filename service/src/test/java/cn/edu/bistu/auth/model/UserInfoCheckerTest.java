package cn.edu.bistu.auth.model;

import cn.edu.bistu.constants.Role;
import cn.edu.bistu.model.entity.User;
import cn.edu.bistu.model.UserInfoChecker;
import cn.edu.bistu.model.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserInfoCheckerTest {

    @Autowired
    UserInfoChecker userInfoChecker;

    @Test
    public void checkFrontUserInfo() {
        UserVo userVo = new UserVo();
        userVo.setRole(Role.UNDERGRADUATE);
        userVo.setStudentId(2382734l);
        userVo.setClassName("软工1902");
        userVo.setGrade(2);
        userVo.setMajorName("软件工程");
        userVo.setName("wc");
        userVo.setCollegeName("计算机学院");

        userInfoChecker.setUser(userVo);

        boolean b = false;
        try {
            b = userInfoChecker.checkFrontUserInfo();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(b);

    }
}
