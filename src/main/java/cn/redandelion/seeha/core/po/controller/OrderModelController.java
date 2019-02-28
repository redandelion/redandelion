package cn.redandelion.seeha.core.po.controller;

import cn.redandelion.seeha.core.po.dto.OrderDetail;
import cn.redandelion.seeha.core.po.dto.OrderModel;
import cn.redandelion.seeha.core.po.service.IOrderDetailService;
import cn.redandelion.seeha.core.po.service.IOrderModelService;
import cn.redandelion.seeha.core.supplier.dto.Supplier;
import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.user.dto.User;
import cn.redandelion.seeha.core.user.service.IUserService;
import cn.redandelion.seeha.core.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("po/order")
public class OrderModelController extends BaseController {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private IOrderModelService service;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderDetailService detailService;
    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResource(HttpServletRequest request, OrderModel orderModel,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        List<OrderModel> models = service.orderQuery(requestContext, orderModel, page, pagesize);
        return new ResponseData(models);
    }
    @PostMapping(value = "/submit")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<OrderModel> orderModels,
                                       BindingResult result) throws Exception {

        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        return new ResponseData(service.batchUpdate(requestContext, orderModels));
    }
    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<OrderModel> orderModels)
            throws Exception {
//        删除子表
        orderModels.forEach(x->{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(x.getOrderId());
            detailService.batchDeleteByForeikey(orderDetail);
        });
        service.batchDelete(orderModels);
        return new ResponseData();
    }
//    新建采购订单
    @PostMapping(value = "/new")
    public ResponseData saveSupplierInof(HttpServletRequest request, @RequestBody List<Supplier> suppliers,
                                         BindingResult result)throws Exception {
        List<Supplier> list = new ArrayList<>();
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;

        }

        ResponseData responseData = new ResponseData();
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        Boolean flagNew= service.orderNew(requestContext,suppliers);
        responseData.setRows(list);
        responseData.setSuccess(flagNew);
        return responseData;

    }
    @PostMapping(value = "/check")
    public ResponseData ordercheckChain(HttpServletRequest request, @RequestBody List<OrderModel> orderModels,
                                         BindingResult result)throws Exception {
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        service.ordercheckChain(requestContext,orderModels);
        return new ResponseData(orderModels);
    }
}
