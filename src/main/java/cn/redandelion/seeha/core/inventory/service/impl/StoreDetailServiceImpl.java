package cn.redandelion.seeha.core.inventory.service.impl;

import cn.redandelion.seeha.core.inventory.dto.Store;
import cn.redandelion.seeha.core.inventory.dto.StoreDetail;
import cn.redandelion.seeha.core.inventory.service.IStoreDetailService;
import cn.redandelion.seeha.core.inventory.service.IStoreService;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class StoreDetailServiceImpl extends BaseServiceImpl<StoreDetail> implements IStoreDetailService {
}
