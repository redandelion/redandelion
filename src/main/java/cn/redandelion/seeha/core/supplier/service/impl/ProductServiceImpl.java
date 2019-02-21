package cn.redandelion.seeha.core.supplier.service.impl;

import cn.redandelion.seeha.core.supplier.dto.Product;
import cn.redandelion.seeha.core.supplier.service.IProductService;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl extends BaseServiceImpl<Product> implements IProductService{
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Product> batchUpdate(IRequest request, List<Product> list) {
        list.forEach(x -> {if (x.getProductId()==null){
            this.insertSelective(request,x);
        }else {
            this.updateByPrimaryKeySelective(request,x);
        }
        });
        return list;
    }

}
