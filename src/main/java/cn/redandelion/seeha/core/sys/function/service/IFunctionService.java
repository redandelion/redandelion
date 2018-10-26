package cn.redandelion.seeha.core.sys.function.service;

import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.IBaseService;
import cn.redandelion.seeha.core.sys.function.dto.Function;
import cn.redandelion.seeha.core.sys.function.dto.MenuItem;

import java.util.List;


public interface IFunctionService extends IBaseService<Function>{
    /**
     * 查询当前登录用户角色合并后的菜单.
     *
     *
     * @param request IRequest
     * @return 当前登录用户角色合并后的菜单集合
     */
    List<MenuItem> selectRoleFunctions(IRequest request);
}
