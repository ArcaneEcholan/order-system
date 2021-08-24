package cn.edu.bistu.flow.service.impl;

import cn.edu.bistu.flow.mapper.FlowNodeMapper;
import cn.edu.bistu.flow.service.FlowNodeService;
import cn.edu.bistu.model.entity.FlowNode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FlowNodeServiceImpl extends ServiceImpl<FlowNodeMapper, FlowNode> implements FlowNodeService {
}