package cn.redandelion.seeha.core.supplier.mapper;

import cn.redandelion.seeha.core.supplier.dto.Product;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ProductMapper extends Mapper<Product> {
}
