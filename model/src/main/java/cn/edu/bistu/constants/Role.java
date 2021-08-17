package cn.edu.bistu.constants;

import java.util.Objects;

public enum Role {

    /**
     * 系统管理员
     */
    ADMIN(0),

    /**
     * 业务员
     */
    OPERATOR(1),

    /**
     * 院级领导
     */
    COLLEGE_LEVEL_LEADER(2),

    /**
     * 部门领导
     */
    DEPT_LEVEL_LEADER(3),

    /**
     * 教师
     */
    TEACHER(4),

    /**
     * 本科生
     */
    UNDERGRADUATE(5),

    /**
     * 研究生
     */
    POSTGRADUATE(6);

    private Integer value;

    Role(Integer value) {
        this.value = value;
    }

    //getter of value
    public Integer getValue() {
        return value;
    }

    /**
     * convert int value to Role constant
     *
     * @return corresponding Role constant of the value; null if the value is invalid
     */
    public static Role valueOf(Integer value) {
        Role[] values = values();
        for (Role role : values) {
            if (Objects.equals(role.getValue(), value)) {
                return role;
            }
        }
        return null;
    }

}
