package cn.redandelion.seeha.core.sys.function.service.impl;

import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import cn.redandelion.seeha.core.sys.function.dto.Function;
import cn.redandelion.seeha.core.sys.function.dto.FunctionRole;
import cn.redandelion.seeha.core.sys.function.dto.MenuItem;
import cn.redandelion.seeha.core.sys.function.service.IFunctionRoleService;
import cn.redandelion.seeha.core.sys.function.service.IFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class FunctionRoleServiceImpl extends BaseServiceImpl<FunctionRole> implements IFunctionRoleService {

    @Autowired
    IFunctionService functionService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FunctionRole> batchUpdate(IRequest request, List<FunctionRole> list) {
        list.forEach(x -> {if (x.getSrfId()==null){
            this.insertSelective(request,x);
        }else {
            this.updateByPrimaryKeySelective(request,x);
        }
        });

        return list;
    }

    @Override
    public List<MenuItem> getAllMenus() {
//        获取所有功能
        List<Function> functions = functionService.selectAll();
//        转换为List<MenuItem>
//       3.将功能换成菜单
        List<MenuItem> menuItemList = functionService.createMenuItem(functions);
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

    @Override
    public List<FunctionRole> buildFunctionAndRole(IRequest request,List<FunctionRole> functionRoles) {
//      删除原来角色所有的关联

        FunctionRole functionRole = new FunctionRole();
        functionRole.setRoleId(functionRoles.get(0).getRoleId());
        this.batchDeleteByForeikey(functionRole);
//      从新建立关联
        functionRoles = this.batchUpdate(request,functionRoles);
        return functionRoles;
    }

}
