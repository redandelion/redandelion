package cn.redandelion.seeha.core.sys.service.impl;

import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import cn.redandelion.seeha.core.sys.dto.FunctionResource;
import cn.redandelion.seeha.core.sys.service.IFunctionResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class FunctionResourceServiceImpl extends BaseServiceImpl<FunctionResource> implements IFunctionResourceService {
}
