package cn.redandelion.seeha.core.sys.service.impl;

import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import cn.redandelion.seeha.core.sys.dto.FunctionRole;
import cn.redandelion.seeha.core.sys.service.IFunctionRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class FunctionRoleServiceImpl extends BaseServiceImpl<FunctionRole> implements IFunctionRoleService {
}
