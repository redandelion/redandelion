package cn.redandelion.seeha.core.po.service.impl;

import cn.redandelion.seeha.core.inventory.dto.StoreDetail;
import cn.redandelion.seeha.core.inventory.service.IStoreDetailService;
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

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements IOrderDetailService {
    @Autowired
    private IProductService productService;
    @Autowired
    private IStoreDetailService storeDetailService;
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
//        存库现有量


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
        if (orderDetail.getInventory()!=null) {
            StoreDetail storeDetail = new StoreDetail();
            storeDetail.setStoreId(Long.valueOf(orderDetail.getInventory()));
            details.forEach(x->{
//                根据仓库以及产品，找到库存
//                根据产品id的条件， 找到所有的相同的产品
                Product product = new Product();
                Product productTemp = new Product();
                List<StoreDetail> storeDetailList = new ArrayList<>();
                product.setProductId(x.getProductId());
                product = productService.selectByPrimaryKey(requestContext, product);
//                产品唯一标识 生产商 + 产品名称
                productTemp.setProducer(product.getProducer());
                productTemp.setProductName(product.getProductName());

                List<Product> products = productService.selectByCondition(productTemp);
                products.forEach(y->{
                    storeDetail.setProductId(y.getProductId());
                    List<StoreDetail> storeDetails = storeDetailService.selectByCondition(storeDetail);
                    if (storeDetails.size()>0) {
                        storeDetailList.addAll(storeDetails);
                    }
                });
                if (storeDetailList.size()>0){
//                    求和  该仓库所有的 商品现有量
                    x.setInventory(storeDetailList.stream().mapToInt(StoreDetail::getNum).sum());
                }else {
                    x.setInventory(0);
                }

            });
        }
        return details;
    }
}
