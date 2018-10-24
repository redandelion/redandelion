package cn.redandelion.seeha.core.sys.basic.service.impl;

import cn.redandelion.seeha.core.annotation.StdWho;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.IBaseService;

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


}
