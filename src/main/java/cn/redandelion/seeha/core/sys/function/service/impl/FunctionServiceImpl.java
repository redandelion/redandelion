package cn.redandelion.seeha.core.sys.function.service.impl;

import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import cn.redandelion.seeha.core.sys.function.dto.Function;
import cn.redandelion.seeha.core.sys.function.dto.FunctionRole;
import cn.redandelion.seeha.core.sys.function.dto.MenuItem;
import cn.redandelion.seeha.core.sys.function.dto.Resource;
import cn.redandelion.seeha.core.sys.function.service.IFunctionRoleService;
import cn.redandelion.seeha.core.sys.function.service.IFunctionService;
import cn.redandelion.seeha.core.sys.function.service.IResourceService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional(rollbackFor = Exception.class)
public class FunctionServiceImpl extends BaseServiceImpl<Function> implements IFunctionService {

    @Autowired
    IFunctionService functionService;
    @Autowired
    IFunctionRoleService functionRoleService;
    @Autowired
    IResourceService resourceService;
    /**
     *
     * @param request IRequest
     * @return 返回所有角色的菜单集合。
     */
    @Override
    public List<MenuItem> selectRoleFunctions(IRequest request) {
          List<FunctionRole> functionList = new ArrayList<>();
          List<Function> functions = new ArrayList<>();
//        1.按照角色遍历所有的功能

          Set<Long> allRoleFunctionIds = new HashSet<>();
          Long[] allRoleId = request.getAllRoleId();
          List<Long> ids = new ArrayList<>();
          for(Long roleId : allRoleId){
              FunctionRole functionRole = new FunctionRole();
              functionRole.setRoleId(roleId);
              functionList = functionRoleService.selectByCondition(functionRole);
              for (FunctionRole fr : functionList){
                  ids.add(fr.getFunctionId());
              }
              allRoleFunctionIds.addAll(ids);
          }

//        2.去除重复项
          functions = functionService.selectAll();
        Map<Long, Function> functionMap = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(functions)) {
            for (Function f : functions) {
                functionMap.put(f.getFunctionId(), f);
            }
        }
//        3.利用hashmap特性生成多级菜单
        Map<Long, MenuItem> menuMap = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(allRoleFunctionIds)) {
            for (Long functionId : allRoleFunctionIds) {
                createMenuRecursive(menuMap, functionMap, functionId);
            }
        }

        List<MenuItem> itemList = new ArrayList<>();
        menuMap.forEach((k, v) -> {
            if (v.getParent() == null) {
                itemList.add(v);
            }
            if (v.getChildren() != null) {
                Collections.sort(v.getChildren());
            }
        });
        Collections.sort(itemList);
        return itemList;
    }
    private MenuItem createMenuRecursive(Map<Long, MenuItem> menuMap, Map<Long, Function> functionMap, Long functionId) {
        MenuItem menuItem = menuMap.get(functionId);
        if (menuItem == null) {
            Function function = functionMap.get(functionId);
            if (function == null) {
                // role has a function that dose not exists.
                return null;
            }
            menuItem = createMenuItem(function);
            menuMap.put(functionId, menuItem);
            // create parent menuItem
            Long parentId = function.getParentFunctionId();
            if (parentId != null) {
                MenuItem parentMenuItem = createMenuRecursive(menuMap, functionMap, parentId);
                if (parentMenuItem != null) {
                    List<MenuItem> children = parentMenuItem.getChildren();
                    if (children == null) {
                        children = new ArrayList<>();
                        parentMenuItem.setChildren(children);
                    }
                    menuItem.setParent(parentMenuItem);
                    children.add(menuItem);
                }
            }
        }
        return menuItem;
    }
    private MenuItem createMenuItem(Function function) {
        MenuItem menu = new MenuItem();
        menu.setText(function.getFunctionName());
        menu.setIcon(function.getFunctionIcon());
        menu.setFunctionCode(function.getFunctionCode());
        if (function.getResourceId() != null) {
            Resource resource = new Resource();
            resource.setResourceId(function.getResourceId());
            resource = resourceService.selectByPrimaryKey(null,resource);
            if (resource != null) {
                menu.setUrl(resource.getUrl());
            }
        }
        menu.setId(function.getFunctionId());
        menu.setScore(function.getFunctionSequence());
        return menu;
    }
}
