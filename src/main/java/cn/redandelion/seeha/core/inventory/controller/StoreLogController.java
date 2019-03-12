package cn.redandelion.seeha.core.inventory.controller;


import cn.redandelion.seeha.core.inventory.dto.StoreLog;

import cn.redandelion.seeha.core.inventory.service.IStoreLogService;
import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.ServiceRequest;
import cn.redandelion.seeha.core.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("inv/log")
public class StoreLogController extends BaseController {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private IStoreLogService service;

    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResource(HttpServletRequest request, StoreLog storeLog,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);
        return new ResponseData(service.select(requestContext, storeLog, page, pagesize));
    }
    @PostMapping(value = "/submit")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<StoreLog> storeLogs,
                                       BindingResult result) throws Exception {

        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);
        return new ResponseData(service.batchUpdate(requestContext, storeLogs));
    }
    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<StoreLog> storeLogs)
            throws Exception {
        service.batchDelete(storeLogs);
        return new ResponseData();
    }
}
