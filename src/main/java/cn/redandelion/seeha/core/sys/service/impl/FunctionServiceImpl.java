package cn.redandelion.seeha.core.sys.service.impl;

import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import cn.redandelion.seeha.core.sys.dto.Function;
import cn.redandelion.seeha.core.sys.service.IFunctionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class FunctionServiceImpl extends BaseServiceImpl<Function> implements IFunctionService {
}
