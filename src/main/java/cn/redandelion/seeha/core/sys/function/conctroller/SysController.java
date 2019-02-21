package cn.redandelion.seeha.core.sys.function.conctroller;

import cn.redandelion.seeha.core.supplier.dto.Supplier;
import cn.redandelion.seeha.core.supplier.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/sys")
public class SysController {
    @Autowired
    private ISupplierService supplierService;


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
}
