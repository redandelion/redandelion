package cn.redandelion.seeha.core.po.service.impl;

import cn.redandelion.seeha.core.po.dto.OrderDetail;
import cn.redandelion.seeha.core.po.service.IOrderDetailService;
import cn.redandelion.seeha.core.po.service.IOrderModelService;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements IOrderDetailService {
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
}
