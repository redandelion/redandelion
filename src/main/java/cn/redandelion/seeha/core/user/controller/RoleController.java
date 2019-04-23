package cn.redandelion.seeha.core.user.controller;

import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.Code;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.ServiceRequest;
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private IRoleService service;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private ApplicationContext context;

    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResource(HttpServletRequest request, Role role,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);

        return new ResponseData(service.select(requestContext, role, page, pagesize));
    }
    @PostMapping(value = "/submit")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<Role> roles,
                                       BindingResult result) throws Exception {
//        getValidator().validate(resources, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);
        return new ResponseData(service.batchUpdate(requestContext, roles));
    }

    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<Role> roles)
            throws Exception {
        service.batchDelete(roles);
        return new ResponseData();
    }
}

