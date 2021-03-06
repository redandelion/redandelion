package cn.redandelion.seeha.core.sys.function.service.impl;

import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import cn.redandelion.seeha.core.sys.function.dto.FunctionResource;
import cn.redandelion.seeha.core.sys.function.service.IFunctionResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FunctionResourceServiceImpl extends BaseServiceImpl<FunctionResource> implements IFunctionResourceService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FunctionResource> batchUpdate(IRequest request, List<FunctionResource> list) {
        list.forEach(x -> {if (x.getResourceId()==null){
            this.insertSelective(request,x);
        }else {
            this.updateByPrimaryKeySelective(request,x);
        }
        });
        return list;
    }
}
