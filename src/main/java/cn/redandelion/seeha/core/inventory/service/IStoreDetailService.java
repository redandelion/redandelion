package cn.redandelion.seeha.core.inventory.service;


import cn.redandelion.seeha.core.inventory.dto.ChatsOfStore;
import cn.redandelion.seeha.core.inventory.dto.StoreDetail;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.IBaseService;

import java.util.List;

public interface IStoreDetailService extends IBaseService<StoreDetail> {
    List<ChatsOfStore> getChats(IRequest iRequest,Long productId);
}
