package cn.redandelion.seeha.core.sys.basic.service.impl;

import cn.redandelion.seeha.core.annotation.StdWho;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.IBaseService;

import cn.redandelion.seeha.core.user.dto.UserRole;
import cn.redandelion.seeha.core.user.service.IUserRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public  class BaseServiceImpl<T> implements IBaseService<T>{
    @Autowired
    protected Mapper<T> mapper;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public void setRoleOfRequest(IRequest iRequest, Long userId) {
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

    @Override
    public List<T> select(IRequest request, T condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.select(condition);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @StdWho(who = true)
    public T insert(IRequest request, T record) {
        mapper.insert(record);
        return record;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @StdWho
    public T insertSelective(IRequest request, T record) {
        mapper.insertSelective(record);
        return record;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @StdWho
    public T updateByPrimaryKey(IRequest request, T record) {
        mapper.updateByPrimaryKey(record);
        return record;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @StdWho
    public T updateByPrimaryKeySelective(IRequest request, T record) {
        mapper.updateByPrimaryKeySelective(record);
        return record;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public T selectByPrimaryKey(IRequest request, T record) {

        return mapper.selectByPrimaryKey(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(T record) {
        int ret = mapper.deleteByPrimaryKey(record);
        return ret;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<T> selectAll() {

        return mapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<T> selectAll(IRequest iRequest) {
        return mapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<T> selectByCondition(T record) {

        return mapper.select(record);
    }

    @Override
    public List<T> selectByExample(Object record) {
        List<T> list = mapper.selectByExample(record);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<T> batchUpdate(IRequest request, List<T> list) {
        list.forEach(x->{
            T t = mapper.selectByPrimaryKey(x);
            if (t!=null){
                mapper.updateByPrimaryKeySelective(x);
            }else {
                mapper.insertSelective(x);
            }
        });
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDelete(List<T> list) {
        int  num = 0;
        for (T e :list) {
            num += mapper.deleteByPrimaryKey(e);
        }
        return num;
    }

    @Override
    public int batchDeleteByForeikey(T record) {

        return  mapper.delete(record);
    }


}
