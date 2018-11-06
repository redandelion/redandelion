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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


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
              functionList.forEach((v)->ids.add(v.getFunctionId()));
              allRoleFunctionIds.addAll(ids);
          }
//        2.返回所有功能集合
        functions = functionService.selectAll();
        List<Function> collect = functions.stream()
                .filter(x -> {
                    return allRoleFunctionIds.stream().anyMatch(y -> y.equals(x.getFunctionId()));
                })
                .collect(Collectors.toList());
//       3.将功能换成菜单
        List<MenuItem> menuItemList = createMenuItem(collect);
//       4.流机制排列菜单
//        4.1 分组
        Map<Long, List<MenuItem>> menu = menuItemList.stream()
                .filter(x -> x.getShortcutId() != null).sorted()
                .collect(Collectors.groupingBy(MenuItem::getShortcutId));
//        4.2 合并子菜单
           menuItemList.forEach(x->{
            if(Optional.ofNullable(menu.get(x.getId())).isPresent()){
                x.setChildren(menu.get(x.getId()));
            }
        });
//        4.3 取出父菜单
        List<MenuItem> itemList = new ArrayList<>();
        itemList = menuItemList.stream()
                .filter(x -> x.getShortcutId() == null)
                .sorted()
                .collect(Collectors.toList());
        return itemList;
    }
    private List<MenuItem> createMenuItem(List<Function> collect) {
        List<MenuItem> list = new ArrayList<>();
        for (Function function : collect) {
            MenuItem menu = new MenuItem();
            menu.setText(function.getFunctionName());
            menu.setIcon(function.getFunctionIcon());
            menu.setFunctionCode(function.getFunctionCode());
            if (function.getResourceId() != null) {
                Resource resource = new Resource();
                resource.setResourceId(function.getResourceId());
                resource = resourceService.selectByPrimaryKey(null, resource);
                if (resource != null) {
                    menu.setUrl(resource.getUrl());
                }
            }
            menu.setId(function.getFunctionId());
            menu.setScore(function.getFunctionSequence());
            menu.setShortcutId(function.getParentFunctionId());
            list.add(menu);
        }
        return list;
    }
}
