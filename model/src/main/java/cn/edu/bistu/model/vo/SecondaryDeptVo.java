package cn.edu.bistu.model.vo;

import cn.edu.bistu.model.entity.SecondaryDept;
import lombok.Data;

@Data
public class SecondaryDeptVo extends SecondaryDept {
    private CollegeVo college;
}