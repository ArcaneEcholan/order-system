package cn.edu.bistu.model.vo;

import cn.edu.bistu.constants.Role;
import cn.edu.bistu.model.entity.User;
import lombok.Data;


/**
 * 与前端交互的对象
 */
@Data
public class UserVo extends User {

    private String collegeName;
    private String majorName;
    private String className;
    private String secondaryDeptName;

}
