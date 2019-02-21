package cn.redandelion.seeha.core.supplier.service.impl;

import cn.redandelion.seeha.core.supplier.dto.Product;
import cn.redandelion.seeha.core.supplier.dto.Supplier;
import cn.redandelion.seeha.core.supplier.service.IProductService;
import cn.redandelion.seeha.core.supplier.service.ISupplierService;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierServiceImpl extends BaseServiceImpl<Supplier> implements ISupplierService {

    @Autowired
    private IProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Supplier> batchUpdate(IRequest request, List<Supplier> list) {
        list.forEach(x -> {if (x.getSupplierId()==null){
            this.insertSelective(request,x);
        }else {
            this.updateByPrimaryKeySelective(request,x);
        }
        });
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Supplier> saveInfo(IRequest request, List<Supplier> list) throws Exception {
        Supplier supplier = new Supplier();
        if (list.get(0).getSupplierId()==-1){
//            insert
            list.get(0).setSupplierId(null);
            supplier = this.insertSelective(request,list.get(0));
        }else {
//            update
            supplier = this.updateByPrimaryKeySelective(request,list.get(0));
        }
        Long supplierId = supplier.getSupplierId();
//       子表
        if (list.get(0).getProductList()!=null || list.get(0).getProductList().size()>0){

            List<Product> productsInsert= list.get(0).getProductList().stream().filter(x->x.getProductId()==null).collect(Collectors.toList());
            List<Product> productsUpdate= list.get(0).getProductList().stream().filter(x->x.getProductId()!=null).collect(Collectors.toList());
            if (productsInsert.size()>0) {
                productsInsert.forEach(x -> {
                    x.setSupplierId(supplierId);
                    productService.insert(request, x);
                });
            }
            if (productsUpdate.size()>0) {
                productService.batchUpdate(request, productsUpdate);
            }
        }
        return list;
    }
}
