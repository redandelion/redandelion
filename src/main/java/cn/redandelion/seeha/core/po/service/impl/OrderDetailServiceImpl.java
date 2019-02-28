package cn.redandelion.seeha.core.po.service.impl;

import cn.redandelion.seeha.core.po.dto.OrderDetail;
import cn.redandelion.seeha.core.po.service.IOrderDetailService;
import cn.redandelion.seeha.core.po.service.IOrderModelService;
import cn.redandelion.seeha.core.supplier.dto.Product;
import cn.redandelion.seeha.core.supplier.service.IProductService;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements IOrderDetailService {
    @Autowired
    private IProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OrderDetail> batchUpdate(IRequest request, List<OrderDetail> list) {
        list.forEach(x -> {if (x.getOrderDetailId()==null){
            this.insertSelective(request,x);
        }else {
            this.updateByPrimaryKeySelective(request,x);
        }
        });
        return list;
    }

    @Override
    public List<OrderDetail> selectQuery(IRequest requestContext, OrderDetail orderDetail, int page, int pagesize) {
        List<OrderDetail> details = this.select(requestContext, orderDetail, page, pagesize);
        details.forEach(x->{
            if (x.getProductId()!=null){
                Product product = new Product();
                product.setProductId(x.getProductId());
                product = productService.selectByPrimaryKey(requestContext, product);
                if (product!=null && product.getProductId()!=null){
                    x.setProductName(product.getProductName());
                }else {
                    x.setProductName(x.getProductId().toString());
                }
            }

        });
        return details;
    }
}
