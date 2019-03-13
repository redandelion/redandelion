package cn.redandelion.seeha.core.inventory.service.impl;

import cn.redandelion.seeha.core.inventory.dto.ChatsOfStore;
import cn.redandelion.seeha.core.inventory.dto.Store;
import cn.redandelion.seeha.core.inventory.dto.StoreDetail;
import cn.redandelion.seeha.core.inventory.dto.StoreLog;
import cn.redandelion.seeha.core.inventory.service.IStoreDetailService;
import cn.redandelion.seeha.core.inventory.service.IStoreLogService;
import cn.redandelion.seeha.core.inventory.service.IStoreService;
import cn.redandelion.seeha.core.supplier.dto.Product;
import cn.redandelion.seeha.core.supplier.service.IProductService;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.BaseServiceImpl;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

@Service
@Transactional(rollbackFor = Exception.class)
public class StoreDetailServiceImpl extends BaseServiceImpl<StoreDetail> implements IStoreDetailService {
    @Autowired
    private IProductService productService;
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IStoreLogService storeLogService;
    @Override
    @Transactional
    public List<ChatsOfStore> getChats(IRequest iRequest, Long productId) {
        StoreDetail storeDetail = new StoreDetail();
        List<ChatsOfStore> chatsOfStores = new ArrayList<>();
        List<ChatsOfStore> result = new ArrayList<>();
        ChatsOfStore chatsOfStore = new ChatsOfStore();
//        Product product = new Product();
//        storeDetail.setProductId(productId);
        List<StoreDetail> storeDetails = this.selectAll();
        Integer sum = storeDetails.stream().mapToInt(StoreDetail::getNum).sum();
//        product.setProductId(productId);
//        product =  productService.selectByPrimaryKey(iRequest,product);
//        if (product!=null) {
//        String productName = product.getProductName();
//        chatsOfStore.setProductName(productName);
            chatsOfStore.setTotal(sum);
            chatsOfStore.setInventoryName("In Stock");
            chatsOfStores.add(chatsOfStore);
            List<StoreLog> storeLogs = storeLogService.selectAll();
            //       按照仓库分类
            Map<String, List<StoreLog>> collect = storeLogs.stream()
                    .collect(Collectors.groupingBy(StoreLog::toString));
    //       分类求和
            collect.forEach((x,y)->{
//                设置仓库名
                Store store = new Store();
                store.setStoreId(Long.parseLong(x.substring(0,x.length()-1)));
                store = storeService.selectByPrimaryKey(iRequest, store);
                ChatsOfStore chatsOfStore1 = new ChatsOfStore();
                chatsOfStore1.setInventoryName(store.getName());
                int sumInv = y.stream().mapToInt(StoreLog::getNum).sum();
                if (x.substring(x.length()-1,x.length()).equals("1")){
//                    出库取相反数
                    sumInv = -sumInv;
                }
//              设置产品名 与没和仓库的总量
                chatsOfStore1.setTotal(sumInv);
//                加入返回值list
                chatsOfStores.add(chatsOfStore1);
            });
        Map<String, List<ChatsOfStore>> collectOfChat = chatsOfStores.stream().
                         collect(Collectors.groupingBy(ChatsOfStore::getInventoryName));


            collectOfChat.forEach((x,y)->{
                ChatsOfStore chat= new ChatsOfStore();
                chat.setInventoryName(x);
                int sumInv = y.stream().mapToInt(ChatsOfStore::getTotal).sum();
                chat.setTotal(sumInv);
                result.add(chat);
            });
           result.sort(comparingInt(ChatsOfStore::getTotal).reversed());
//        }

        return result;
    }
}
