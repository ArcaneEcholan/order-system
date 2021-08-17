package cn.edu.bistu.auth.mapper;

import cn.edu.bistu.model.entity.User;
import cn.edu.bistu.model.vo.UserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    UserVo selectByOpenId(String openId);

    Integer updateUserById(UserVo userVo);
}
