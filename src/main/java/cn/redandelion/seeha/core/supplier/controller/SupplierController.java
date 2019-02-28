package cn.redandelion.seeha.core.supplier.controller;

import cn.redandelion.seeha.core.supplier.dto.Product;
import cn.redandelion.seeha.core.supplier.dto.Supplier;
import cn.redandelion.seeha.core.supplier.service.IProductService;
import cn.redandelion.seeha.core.supplier.service.ISupplierService;
import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.Code;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.function.service.IResourceService;
import cn.redandelion.seeha.core.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("supplier")
public class SupplierController extends BaseController{
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ApplicationContext context;

    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResource(HttpServletRequest request, Supplier supplier,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        return new ResponseData(supplierService.select(requestContext, supplier, page, pagesize));
    }

    @PostMapping(value = "/submit")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<Supplier> suppliers,
                                       BindingResult result) throws Exception {
//        getValidator().validate(resources, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        return new ResponseData(supplierService.batchUpdate(requestContext, suppliers));
    }

    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<Supplier> suppliers)
            throws Exception {
        suppliers.forEach(x->{
            Product product = new Product();
            product.setSupplierId(x.getSupplierId());
            productService.batchDeleteByForeikey(product);
        });


        supplierService.batchDelete(suppliers);

        return new ResponseData();
    }
    /**
     *
     */
    @PostMapping(value = "/supplierInfo")
    public ResponseData saveSupplierInof(HttpServletRequest request, @RequestBody List<Supplier> suppliers,
                                         BindingResult result)throws Exception {
        List<Supplier> list = new ArrayList<>();
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");

        return new ResponseData(supplierService.saveInfo(requestContext,suppliers));
    }
/**
 * 供应商值列表
  */
    @RequestMapping(value = "/code")
    @ResponseBody
    public List<Code> supplierCode(HttpServletResponse response ) throws IOException {
        List<Code> codeList = new ArrayList<>();
        List<Supplier>  suppliers = supplierService.selectAll();
        if (suppliers.size()>0){
            suppliers.forEach(x->{
                Code code = new Code();
                code.setValue(x.getSupplierId());
                code.setMeaning(x.getFullName());
                codeList.add(code);
            });
        }
        return codeList;
    }
}
