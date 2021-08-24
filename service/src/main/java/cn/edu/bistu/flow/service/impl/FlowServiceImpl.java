package cn.edu.bistu.flow.service.impl;

import cn.edu.bistu.flow.mapper.FlowMapper;
import cn.edu.bistu.flow.service.FlowService;
import cn.edu.bistu.model.entity.Flow;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FlowServiceImpl extends ServiceImpl<FlowMapper, Flow> implements FlowService  {
}
