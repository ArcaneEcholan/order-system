package cn.edu.bistu.model.vo;

import cn.edu.bistu.model.entity.Clazz;
import lombok.Data;

@Data
public class ClazzVo extends Clazz {
    private MajorVo major;
}
