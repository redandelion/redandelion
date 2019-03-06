package cn.redandelion.seeha.core.sys.function.conctroller;

import cn.redandelion.seeha.core.inventory.dto.Store;
import cn.redandelion.seeha.core.inventory.service.IStoreService;
import cn.redandelion.seeha.core.po.dto.OrderModel;
import cn.redandelion.seeha.core.po.service.IOrderModelService;
import cn.redandelion.seeha.core.supplier.dto.Supplier;
import cn.redandelion.seeha.core.supplier.service.ISupplierService;
import cn.redandelion.seeha.core.sys.basic.dto.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/sys")
public class SysController {
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IOrderModelService orderModelService;

    @Autowired
    private IStoreService storeService;
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/supplier")
    public String supplier(){
        return "supplier/supplier";
    }
    @RequestMapping("/product")
    public String product(){
        return "supplier/product";
    }

    @RequestMapping("/product_edit/{supplierId}")
    public String productEdit(ModelMap map,@PathVariable(required = false) Long supplierId){
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierId);
        if(supplierId!=-1) {

            supplier = supplierService.selectByPrimaryKey(null, supplier);
            map.put("supplier", supplier);
        }else {

            map.put("supplier",supplier);
        }
         return "supplier/product_edit";
    }
    @RequestMapping("/po/{orderState}")
    public String order(ModelMap map,@PathVariable String orderState){
        map.put("orderState",orderState);
        return "po/order";
    }
    /**
     *  订单详情
     */
    @RequestMapping("/po/detail")
    public String orderDetail(){
        return "po/order_detail";
    }

    /**
     *  新建采购订单
     */

    @RequestMapping("/po/new")
    public String orderNew(Model map){

        return "po/order_new";
    }
/**
 *  编辑采购订单
  */

@RequestMapping("/po/edit/{orderState}/{orderId}")
public String OrderEdit(ModelMap map,@PathVariable(required = false) Long orderId,
                        @PathVariable(required = false) int orderState){

    OrderModel orderModel = new OrderModel();
    orderModel.setOrderId(orderId);
    orderModel = orderModelService.orderQuery(null,orderModel,0,10).get(0);
    map.put("orderState",orderState);
    map.put("orderModel",orderModel);
    return "po/order_edit";
}
    /**
     *   仓库
     */

    @RequestMapping("/inv")
    public String store(ModelMap map){

        return "inv/store";
    }
    /**
     *   //    仓库编辑
     */
    @RequestMapping("/inv/edit/{storeId}")
    public String storeEdit(ModelMap map,@PathVariable(required = false) Long storeId){
        Store store = new Store();
        store.setStoreId(storeId);
        store = storeService.selectByPrimaryKey(null,store);
        map.put("store",store);
        return "inv/store_edit";
    }
    /**
     *   仓库明细
      */
    @RequestMapping("/inv/detail")
    public String storeDetail(ModelMap map){

        return "inv/store_detail";
    }
    /**
     *   //    仓库日记
     */
    @RequestMapping("/inv/log")
    public String storeLog(ModelMap map){

        return "inv/store_log";
    }
    @RequestMapping("/sys/function")
    public String function(ModelMap map){

        return "sys/sys_function";
    }
//  功能分配
@RequestMapping("/sys/function/role")
public String functionRole(ModelMap map){
//        todo
    map.put("roleId","10002");
    return "sys/sys_role_function";
}

}
