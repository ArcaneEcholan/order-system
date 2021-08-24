package cn.edu.bistu.approval.service.impl;

import cn.edu.bistu.approval.mapper.ApprovalMapper;
import cn.edu.bistu.approval.service.ApprovalService;
import cn.edu.bistu.model.entity.Approval;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ApprovalServiceImpl extends ServiceImpl<ApprovalMapper, Approval> implements ApprovalService {
}
