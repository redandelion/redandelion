package cn.redandelion.seeha.core.user.controller;

import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.ServiceRequest;
import cn.redandelion.seeha.core.sys.function.conctroller.SysController;
import cn.redandelion.seeha.core.user.dto.Role;
import cn.redandelion.seeha.core.user.dto.User;
import cn.redandelion.seeha.core.user.dto.UserRole;
import cn.redandelion.seeha.core.user.service.IRoleService;
import cn.redandelion.seeha.core.user.service.IUserRoleService;
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

@Controller
@RequestMapping("/role/user")
public class RoleUserController extends BaseController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService service;
    @Autowired
    private ApplicationContext context;

    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResource(HttpServletRequest request, UserRole userRole,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);
        List<UserRole> userRoles = service.selectByCondition(userRole);
        List<Role> roles = new ArrayList<>();
//      遍历所有角色并返回
        userRoles.forEach(x->{
            Role role = new Role();
            role.setRoleId(x.getRoleId());
            role = roleService.selectByPrimaryKey(requestContext, role);
            roles.add(role);
        });
        return new ResponseData(roles);
    }
    @PostMapping(value = "/save")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<UserRole> userRoles,
                                       BindingResult result) throws Exception {

        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);
        List<UserRole> roles = service.selectByCondition(userRoles.get(0));
        if (roles.size()>0) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        return new ResponseData(service.batchUpdate(requestContext, userRoles));
    }
    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<UserRole> userRoles)
            throws Exception {

        userRoles.forEach(x->{
            service.batchDeleteByForeikey(x);
        });


        return new ResponseData();
    }
}

