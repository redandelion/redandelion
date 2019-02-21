package cn.redandelion.seeha.core.supplier.service;


import cn.redandelion.seeha.core.supplier.dto.Supplier;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.IBaseService;

import java.util.List;

public interface ISupplierService extends IBaseService<Supplier> {

    List<Supplier> saveInfo(IRequest request, List<Supplier> list) throws Exception;
}
