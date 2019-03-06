package cn.redandelion.seeha.core.inventory.controller;

import cn.redandelion.seeha.core.inventory.dto.Store;
import cn.redandelion.seeha.core.inventory.dto.StoreDetail;
import cn.redandelion.seeha.core.inventory.service.IStoreDetailService;
import cn.redandelion.seeha.core.inventory.service.IStoreService;
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
@RequestMapping("inv/detail")
public class StoreDetailController extends BaseController {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private IStoreDetailService service;

    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResource(HttpServletRequest request, StoreDetail storeDetail,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        return new ResponseData(service.select(requestContext, storeDetail, page, pagesize));
    }
    @PostMapping(value = "/submit")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<StoreDetail> storeDetails,
                                       BindingResult result) throws Exception {

        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        return new ResponseData(service.batchUpdate(requestContext, storeDetails));
    }
    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<StoreDetail> storeDetails)
            throws Exception {
        service.batchDelete(storeDetails);
        return new ResponseData();
    }
}
