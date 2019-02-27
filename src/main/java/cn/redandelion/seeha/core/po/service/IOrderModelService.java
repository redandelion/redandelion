package cn.redandelion.seeha.core.po.service;

import cn.redandelion.seeha.core.po.dto.OrderModel;
import cn.redandelion.seeha.core.supplier.dto.Supplier;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.IBaseService;

import java.util.List;

public interface IOrderModelService extends IBaseService<OrderModel> {
     Boolean orderNew(IRequest request , List<Supplier> suppliers) throws Exception;

}
