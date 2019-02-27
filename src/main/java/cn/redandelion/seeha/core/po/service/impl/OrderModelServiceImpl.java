package cn.redandelion.seeha.core.po.service.impl;

import cn.redandelion.seeha.core.po.dto.OrderDetail;
import cn.redandelion.seeha.core.po.dto.OrderModel;
import cn.redandelion.seeha.core.po.service.IOrderDetailService;
import cn.redandelion.seeha.core.po.service.IOrderModelService;
import cn.redandelion.seeha.core.supplier.dto.Product;
import cn.redandelion.seeha.core.supplier.dto.Supplier;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import cn.redandelion.seeha.core.util.GetUuidCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderModelServiceImpl extends BaseServiceImpl<OrderModel> implements IOrderModelService  {
    @Autowired
    private IOrderDetailService detailService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OrderModel> batchUpdate(IRequest request, List<OrderModel> list) {
        list.forEach(x -> {if (x.getOrderId()==null){
            this.insertSelective(request,x);
        }else {
            this.updateByPrimaryKeySelective(request,x);
        }
        });
        return list;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean orderNew(IRequest request, List<Supplier> suppliers) throws Exception {
//        1 设置采购订单头，
          OrderModel orderModel = new OrderModel();
//            1.1 获取创建者

        orderModel.setCreater(request.getUserId());
        orderModel.setCreateTime(new Date());
        //  1.2 设置采购订单状态 为0 以及类型 0 为采购订单
        orderModel.setOrderState(0);
        orderModel.setOrderType(0);
        //  1.3 设置供应商
        orderModel.setSupplierId(suppliers.get(0).getSupplierId());
        //  1.4 设置订单头金额与明细条数
        orderModel.setTotalNum(suppliers.get(0).getProductList().size());
        BigDecimal sum = suppliers.get(0).getProductList().stream().map(Product::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        orderModel.setTotalPrice(sum);
        //  1.5 设置订单头流水号
        orderModel.setOrderNum(GetUuidCodeUtil.getUUID16());
//        2 获取采购订单头id
        try {
            orderModel =  this.insertSelective(request,orderModel);
            Long orderId = orderModel.getOrderId();
//        3 插入订单明细表
            suppliers.get(0).getProductList().forEach(x->{
                OrderDetail orderDetail = new OrderDetail();
//            设置订单头ID
                orderDetail.setOrderId(orderId);
                orderDetail.setProductId(x.getProductId());
                orderDetail.setDetailNum(x.getNumber());
                orderDetail.setDetailPrice(x.getTotalPrice());
                detailService.insertSelective(request,orderDetail);
            });
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return true;
    }
}
