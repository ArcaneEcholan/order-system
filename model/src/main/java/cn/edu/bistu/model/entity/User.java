package cn.edu.bistu.model.entity;

import cn.edu.bistu.constants.Role;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


/**
 * 与数据库交互的对象
 */
@Data
public class User extends BaseEntity{
    @TableField("open_id")
    private String openId;

    @TableField("session_key")
    private String sessionKey;

    private Role role;
    private String name;
    @TableField("info_complete")
    private Integer infoComplete;

    //wx信息
    private Integer gender;
    @TableField("avatar_url")
    private String avatarUrl;
    @TableField("nick_name")
    private String nickName;

    @TableField("college_id")
    private Integer collegeId;

    //学生属性
    @TableField("major_id")
    private Integer majorId;
    @TableField("class_id")
    private Integer classId;
    private Integer grade;
    @TableField("student_id")
    private Long studentId;

    //教师领导属性
    @TableField("secondary_dept_id")
    private Long secondaryDeptId;
    @TableField("job_id")
    private Long jobId;


}
