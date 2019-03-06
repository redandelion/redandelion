package cn.redandelion.seeha.core.inventory.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STORE_DETAIL")
public class StoreDetail extends BaseDto {
    /**
     * 仓库详情 id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long detailId;
    /**
     * 仓库详情 仓库id
     */
    private Long storeId;
    /**
     * 仓库详情 产品id
     */
    private Long productId;
    /**
     * 仓库详情 产品剩余数量
     */
    private Integer num;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
