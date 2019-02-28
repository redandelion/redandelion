package cn.redandelion.seeha.core.po.service;

import cn.redandelion.seeha.core.po.dto.OrderDetail;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.IBaseService;

import java.util.List;

public interface IOrderDetailService extends IBaseService<OrderDetail> {
    List<OrderDetail> selectQuery(IRequest requestContext,OrderDetail orderDetail,int page,int pagesize);
}
