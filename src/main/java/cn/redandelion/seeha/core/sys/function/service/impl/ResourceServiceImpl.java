package cn.redandelion.seeha.core.sys.function.service.impl;

import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import cn.redandelion.seeha.core.sys.function.dto.Resource;
import cn.redandelion.seeha.core.sys.function.service.IResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements IResourceService {

}
