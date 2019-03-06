package cn.redandelion.seeha.core.sys.function.service;

import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.IBaseService;
import cn.redandelion.seeha.core.sys.function.dto.FunctionRole;
import cn.redandelion.seeha.core.sys.function.dto.MenuItem;

import java.util.List;

public interface IFunctionRoleService extends IBaseService<FunctionRole> {
    List<MenuItem> getAllMenus();
    List<FunctionRole> buildFunctionAndRole(IRequest request, List<FunctionRole> functionRoles);
}
