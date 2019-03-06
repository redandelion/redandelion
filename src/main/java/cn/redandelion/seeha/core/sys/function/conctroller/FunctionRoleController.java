package cn.redandelion.seeha.core.sys.function.conctroller;

import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.function.dto.FunctionRole;
import cn.redandelion.seeha.core.sys.function.dto.MenuItem;
import cn.redandelion.seeha.core.sys.function.service.IFunctionRoleService;
import cn.redandelion.seeha.core.sys.function.service.IFunctionService;
import cn.redandelion.seeha.core.util.ResponseData;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = {"/sys/rolefunction", "/api/sys/rolefunction"})
public class FunctionRoleController extends BaseController {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private IFunctionRoleService functionRoleService;
    @Autowired
    private IFunctionService functionService;
    @RequestMapping(value = "/query")
    public ResponseData queryRoleFunction(HttpServletRequest request, @RequestParam(required = false) Long roleId)
            throws Exception {
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");

        List<MenuItem> menus = functionRoleService.getAllMenus();
        if (roleId != null) {

            Long[] ids = functionService.getRoleFunctionById(roleId);
            updateMenuCheck(menus, ids);

        }
        return new ResponseData(menus);
    }
    @PostMapping(value = "/submit")
    public ResponseData submitRoleFunction(HttpServletRequest request, @RequestBody List<FunctionRole> functionRoles)
            throws Exception {
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
//      增加角色功能映射
        functionRoleService.buildFunctionAndRole(requestContext,functionRoles);
        return new ResponseData(functionRoles);
    }



    /**
     * 处理勾选状态.
     *
     * @param menus 菜单
     * @param ids   功能Id集合
     */
    private void updateMenuCheck(final List<MenuItem> menus, final Long[] ids) {
        if (CollectionUtils.isEmpty(menus) || ids.length==0) {
            return;
        }
        for (MenuItem menuItem : menus) {
            if (menuItem.getChildren() != null && !menuItem.getChildren().isEmpty()) {
                updateMenuCheck(menuItem.getChildren(), ids);
            }
            for (Long id : ids) {
                if (menuItem.getId().equals(id)) {
                    menuItem.setIschecked(Boolean.TRUE);
                }
            }
        }
    }
}
