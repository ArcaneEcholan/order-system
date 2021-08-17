package cn.edu.bistu.auth.service.impl;

import cn.edu.bistu.auth.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;


    @Test
    public void convertId2Name() throws Exception {

        //UserDto user = new UserDto();
        //user.setId(2l);
        //user.setMajorId(1);
        //user.setCollegeId(1);
        //user.setJobId(2019012672l);
        //user.setClassId(1);
        //
        //UserDto user1 = userService.convertId2Name(user);
        //user.setNames(user1);
        //
        //System.out.println(user);
    }

}