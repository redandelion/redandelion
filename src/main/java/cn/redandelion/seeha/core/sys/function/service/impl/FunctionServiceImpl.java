package cn.redandelion.seeha.core.sys.function.service.impl;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import cn.redandelion.seeha.core.sys.function.dto.*;
import cn.redandelion.seeha.core.sys.function.service.IFunctionResourceService;
import cn.redandelion.seeha.core.sys.function.service.IFunctionRoleService;
import cn.redandelion.seeha.core.sys.function.service.IFunctionService;
import cn.redandelion.seeha.core.sys.function.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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
    @Autowired
    private IFunctionResourceService functionResourceService;


    /**
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

    @Override
    public List<MenuItem> createMenuItem(List<Function> collect) {
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



    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Function> batchUpdate(IRequest request, List<Function> list) {
        list.forEach(x -> {if (x.getFunctionId()==null){
            this.insertSelective(request,x);
        }else {
            this.updateByPrimaryKeySelective(request,x);
        }
        });

        return list;
    }
//  保存功能与资源的关系
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Function> saveFunctionAndResource(IRequest request, List<Function> list) {
        list.forEach(x->{
            if (x.getFunctionId()!=null && x.getResourceId()!=null){
//                更新functionResource关系表
                FunctionResource functionResource = new FunctionResource();
                functionResource.setFunctionId(x.getFunctionId());
                functionResource.setResourceId(x.getResourceId());
                List<FunctionResource> functionResources = functionResourceService.selectByCondition(functionResource);
                if (functionResources.size()>0){
//                    存在则更新
                    functionResourceService.updateByPrimaryKey(request,functionResources.get(0));
                }else {
//                    插入
                    functionResourceService.insertSelective(request,functionResource);
                }
            }
        });
        this.batchUpdate(request,list);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFunctionAndResource(List<Function> functions)  {
//        如果有资源id ，则需要删除关系表
//        如有是父级菜单，所有的子菜单也要删除
        functions.forEach(x->{
//            删除关系表
            if (x.getResourceId() != null){
                deleteOneFunctionAndResource(x);
            }
            while (x.getResourceId() == null){
//            找到所有子菜单并删除
                Function function = new Function();
                function.setParentFunctionId(x.getFunctionId());
                List<Function> functionList = this.selectByCondition(function);
//                functionList.forEach(y -> {
//                    deleteOneFunctionAndResource(y);
//
//                });
//                删除function表
                this.batchDelete(functionList);
//                向下遍历 如果存在子节点
                if (functionList.size()>0) {
                    deleteFunctionAndResource(functionList);
                }else {
//                    跳出循环
                    break;
                }
            }
        });
//     最后删除最顶级
        this.batchDelete(functions);
    }
    private void deleteOneFunctionAndResource(Function function) {
        FunctionResource  functionResource = new FunctionResource();
        functionResource.setResourceId(function.getResourceId());
        functionResource.setFunctionId(function.getFunctionId());
        List<FunctionResource> functionResources = functionResourceService.selectByCondition(functionResource);
        if (functionResources.size()>0){
            functionResourceService.deleteByPrimaryKey(functionResources.get(0));
        }
    }


    @Override
    public Long[] getRoleFunctionById(Long roleId) {


        FunctionRole functionRole = new FunctionRole();
        functionRole.setRoleId(roleId);
        List<FunctionRole> functionRoles = functionRoleService.selectByCondition(functionRole);
        Long ids[] = new Long[functionRoles.size()];
        for (int i= 0 ;i<functionRoles.size();i++){
            ids[i] = functionRoles.get(i).getFunctionId();
        }
        return ids;
    }
}
