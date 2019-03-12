package cn.redandelion.seeha.core.sys.basic.service;

import cn.redandelion.seeha.core.annotation.StdWho;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface IBaseService<T> {

     void setRoleOfRequest(IRequest iRequest,Long userId);
    List<T> select(IRequest request, T condition, int pageNum, int pageSize);

    T insert(IRequest request,  T record);

    T insertSelective(IRequest request,  T record);

    T updateByPrimaryKey(IRequest request,  T record);

    @Transactional(rollbackFor = Exception.class)
    T updateByPrimaryKeySelective(IRequest request,  T record);


    T selectByPrimaryKey(IRequest request, T record);

    int deleteByPrimaryKey(T record);

    List<T> selectAll();


    List<T> selectAll(IRequest iRequest);
    List<T> selectByCondition(T record);
    List<T> selectByExample(Object record);

    List<T> batchUpdate(IRequest request, @StdWho List<T> list);
    int batchDelete(List<T> list);
    int batchDeleteByForeikey(T record);

}
