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
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService service;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private ApplicationContext context;
    @RequestMapping(value = "index")
    public String index2(ModelMap map) {
        map.put("title", "freemarker hello word  ====");
        // 开头不要加上/，linux下面会出错
        return "index";
    }

    @RequestMapping(value = "resources")
    public String resources() {

        // 开头不要加上/，linux下面会出错
        return "resource";
    }
    @RequestMapping(value = {"resource","resource/{resourceId}"})
    @ResponseBody
    public String resource(ModelMap map, @PathVariable(required = false) String resourceId) {
        map.put("resourceId", resourceId);
        return "sys/sys_resource";
    }

//    @RequestMapping(value = {"resource/edit","resource/edit/{functionId}","resource/edit/{functionId}/{resourceId}"})
//    public String resourceEdit(ModelMap map, @PathVariable(required = false) String resourceId,
//                               @PathVariable(required = false) String functionId) {
//        map.put("title", "freemarker hello word  ====");
//        map.put("resourceId", resourceId);
//        map.put("functionId", functionId);
//        return "sys/sys_resource_edit";
//    }



    @RequestMapping(value = "/code")
    @ResponseBody
    public List<Code> supplierCode(HttpServletResponse response ) throws IOException {
        List<Code> codeList = new ArrayList<>();
        List<User>  users = service.selectAll();
        if (users.size()>0){
            users.forEach(x->{
                Code code = new Code();
                code.setValue(x.getUserId());
                code.setMeaning(x.getUserName());
                codeList.add(code);
            });
        }
        return codeList;
    }
    @RequestMapping(value = "/roleId/code")
    @ResponseBody
    public List<Code> roleCode(HttpServletResponse response ) throws IOException {
        List<Code> codeList = new ArrayList<>();
        List<Role>  roles = roleService.selectAll();
        if (roles.size()>0){
            roles.forEach(x->{
                Code code = new Code();
                code.setValue(x.getRoleId());
                code.setMeaning(x.getRoleName());
                codeList.add(code);
            });
        }
        return codeList;
    }
    public void setRoleOfRequest(IRequest iRequest,Long userId){
        if (userId!=null) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            List<UserRole> userRoles = userRoleService.selectByCondition(userRole);
            if (userRoles.size()>0) {
                Long[] ids = new Long[userRoles.size()];
                for (int i = 0; i < userRoles.size(); i++) {
                    ids[i] = userRoles.get(i).getRoleId();
                }
                iRequest.setUserId(userId);
                iRequest.setRoleId(userRoles.get(0).getRoleId());
                iRequest.setAllRoleId(ids);
            }
        }
    }



    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResource(HttpServletRequest request, User user,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);
        return new ResponseData(service.select(requestContext, user, page, pagesize));
    }
    @PostMapping(value = "/submit")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<User> users,
                                       BindingResult result) throws Exception {
//        getValidator().validate(resources, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);
        return new ResponseData(service.batchUpdate(requestContext, users));
    }

    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<User> users)
            throws Exception {
        service.batchDelete(users);
        return new ResponseData();
    }
}

