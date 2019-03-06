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

    /**
     *
     * @param request
     * @param list
     * @return 保存功能与资源关系
     */
    List<Function> saveFunctionAndResource(IRequest request,List<Function> list);

    /**
     *
     * @param functions
     * 删除功能以及，资源关系
     */

    void deleteFunctionAndResource(List<Function> functions);

    /**
     *
     * @param collect 功能菜单集合
     * @return 返回菜单外部接口
     */
    List<MenuItem> createMenuItem(List<Function> collect);

    /**
     *
     * @param roleId
     * @return 返回所有该用户的功能
     */
    Long[] getRoleFunctionById(Long roleId);
}
