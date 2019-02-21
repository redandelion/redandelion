package cn.redandelion.seeha.core.supplier.controller;

import cn.redandelion.seeha.core.supplier.dto.Product;
import cn.redandelion.seeha.core.supplier.service.IProductService;
import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;
    @Autowired
    private ApplicationContext context;

    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryProduct(HttpServletRequest request, Product product,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        return new ResponseData(productService.select(requestContext,product, page, pagesize));
    }

    @PostMapping(value = "/submit")
    public ResponseData submitProduct(HttpServletRequest request, @RequestBody List<Product> products,
                                       BindingResult result) throws Exception {
//        getValidator().validate(resources, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        return new ResponseData(productService.batchUpdate(requestContext, products));
    }

    @PostMapping(value = "/remove")
    public ResponseData removeProduct(HttpServletRequest request, @RequestBody List<Product> products)
            throws Exception {
        productService.batchDelete(products);
        return new ResponseData();
    }

}
