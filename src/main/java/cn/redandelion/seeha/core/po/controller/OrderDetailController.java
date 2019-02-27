package cn.redandelion.seeha.core.po.controller;

import cn.redandelion.seeha.core.po.dto.OrderDetail;
import cn.redandelion.seeha.core.po.service.IOrderDetailService;
import cn.redandelion.seeha.core.po.service.IOrderModelService;
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
@RequestMapping("po/order/detail/")
public class OrderDetailController extends BaseController {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private IOrderDetailService service;
    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResource(HttpServletRequest request, OrderDetail orderDetail,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        return new ResponseData(service.select(requestContext, orderDetail, page, pagesize));
    }
    @PostMapping(value = "/submit")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<OrderDetail> orderDetails,
                                       BindingResult result) throws Exception {

        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        return new ResponseData(service.batchUpdate(requestContext, orderDetails));
    }
    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<OrderDetail> orderDetails)
            throws Exception {
        service.batchDelete(orderDetails);
        return new ResponseData();
    }

}
