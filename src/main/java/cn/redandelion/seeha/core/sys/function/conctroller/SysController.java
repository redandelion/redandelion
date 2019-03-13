package cn.redandelion.seeha.core.sys.function.conctroller;

import cn.redandelion.seeha.core.inventory.dto.Store;
import cn.redandelion.seeha.core.inventory.service.IStoreService;
import cn.redandelion.seeha.core.po.dto.OrderModel;
import cn.redandelion.seeha.core.po.service.IOrderModelService;
import cn.redandelion.seeha.core.supplier.dto.Supplier;
import cn.redandelion.seeha.core.supplier.service.ISupplierService;
import cn.redandelion.seeha.core.sys.basic.dto.Code;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.ServiceRequest;
import cn.redandelion.seeha.core.user.dto.User;
import cn.redandelion.seeha.core.user.dto.UserRole;
import cn.redandelion.seeha.core.user.service.IUserRoleService;
import cn.redandelion.seeha.core.user.service.IUserService;
import cn.redandelion.seeha.core.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
    private ApplicationContext context;
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping(value = "loginIndex")
    @ResponseBody
    public String loginIndex(HttpServletResponse response, HttpServletRequest request,
                        @RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        List<User> users = userService.selectByCondition(user);
        if (users.size()>0) {
            user = users.get(0);
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            List<UserRole> userRoles = userRoleService.selectByCondition(userRole);
            if (userRoles.size()>0) {
                Long[] ids = new Long[userRoles.size()];
                for (int i = 0; i < userRoles.size(); i++) {
                    ids[i] = userRoles.get(i).getRoleId();
                }
                IRequest iRequest = (IRequest) context.getBean(ServiceRequest.class);
                iRequest.setUserId(user.getUserId());
                iRequest.setRoleId(userRoles.get(0).getRoleId());
                iRequest.setAllRoleId(ids);
                CookieUtils.setRequestFromCookie(iRequest,response);
//                context.getAutowireCapableBeanFactory().createBean(ServiceRequest.class);
            }else {
                return "error1";
            }
        }else {
            return "error";
        }
        // 开头不要加上/，linux下面会出错
        return "success";
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
    @RequestMapping("/po/{orderType}/{orderState}")
    public String order(ModelMap map,@PathVariable String orderState,@PathVariable String orderType){
        map.put("orderState",orderState);
        map.put("orderType",orderType);
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

    @RequestMapping("/po/new/{orderType}")
    public String orderNew(ModelMap map,@PathVariable Integer orderType){
        map.put("orderState",orderType);
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
        User user = new User();
        user.setUserId(store.getStockman());
        user = userService.selectByPrimaryKey(null, user);
        store.setStockmanName(user.getUserName());
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
public String functionRole(ModelMap map,HttpServletRequest request){
//        todo

    IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);
    Long userId = Long.parseLong(CookieUtils.getCookieValue(request, "userId"));
    userRoleService.setRoleOfRequest(requestContext,userId);
    map.put("roleId",requestContext.getRoleId());
    return "sys/sys_role_function";
}
    @RequestMapping("/chats")
    public String chatsOfInv(ModelMap map){
//        todo

        return "test";
    }
}
