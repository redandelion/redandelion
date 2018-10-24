package cn.redandelion.seeha.core.user.mapper;


import cn.redandelion.seeha.core.user.dto.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
@Repository
public interface UserMapper extends Mapper<User>{
    List<User> findAll();
}
