package cn.redandelion.seeha.core.sys.function.mapper;

import cn.redandelion.seeha.core.sys.function.dto.Function;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface FunctionMapper extends Mapper<Function> {

}
