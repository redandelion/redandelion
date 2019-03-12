package cn.redandelion.seeha.core.inventory.controller;

import cn.redandelion.seeha.core.inventory.dto.Store;
import cn.redandelion.seeha.core.inventory.service.IStoreService;
import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.Code;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.ServiceRequest;
import cn.redandelion.seeha.core.util.CookieUtils;
import cn.redandelion.seeha.core.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("inv/")
public class StoreController extends BaseController {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private IStoreService service;

    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResource(HttpServletRequest request, Store store,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);
        return new ResponseData(service.select(requestContext, store, page, pagesize));
    }
    @PostMapping(value = "/submit")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<Store> stores,
                                       BindingResult result) throws Exception {

        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class );
        Long userId = Long.parseLong(CookieUtils.getCookieValue(request, "userId"));
        service.setRoleOfRequest(requestContext,userId);
        return new ResponseData(service.batchUpdate(requestContext, stores));
    }
    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<Store> stores)
            throws Exception {
        service.batchDelete(stores);
        return new ResponseData();
    }
    @RequestMapping(value = "/code")
    @ResponseBody
    public List<Code> supplierCode(HttpServletResponse response ) throws IOException {
        List<Code> codeList = new ArrayList<>();
        List<Store>  users = service.selectAll();
        if (users.size()>0){
            users.forEach(x->{
                Code code = new Code();
                code.setValue(x.getStoreId());
                code.setMeaning(x.getName());
                codeList.add(code);
            });
        }
        return codeList;
    }
}
