package cn.redandelion.seeha.core.sys.function.service.impl;

import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import cn.redandelion.seeha.core.sys.function.dto.Resource;
import cn.redandelion.seeha.core.sys.function.service.IResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements IResourceService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Resource> batchUpdate(IRequest request, List<Resource> list) {
        list.forEach(x -> {if (x.getResourceId()==null){
            this.insertSelective(request,x);
        }else {
            this.updateByPrimaryKeySelective(request,x);
        }
        });
        return list;
    }
}
