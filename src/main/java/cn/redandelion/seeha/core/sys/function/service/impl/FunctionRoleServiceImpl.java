package cn.redandelion.seeha.core.sys.function.service.impl;

import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import cn.redandelion.seeha.core.sys.function.dto.FunctionRole;
import cn.redandelion.seeha.core.sys.function.service.IFunctionRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FunctionRoleServiceImpl extends BaseServiceImpl<FunctionRole> implements IFunctionRoleService {
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
}
