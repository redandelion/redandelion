package cn.redandelion.seeha.core.inventory.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.*;

@Entity
@Table(name = "STORE")
public class Store extends BaseDto {
    /**
     *  仓库Id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long storeId;
    /**
     *  仓库 管理员
     */
    private Long stockman;
    /**
     *  仓库 名称
     */
    private String name;
    /**
     *  仓库 地址
     */
    private String address;
    /**
     *  仓库 管理员名称
     */
    @Transient
    private String stockmanName;
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getStockman() {
        return stockman;
    }

    public void setStockman(Long stockman) {
        this.stockman = stockman;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStockmanName() {
        return stockmanName;
    }

    public void setStockmanName(String stockmanName) {
        this.stockmanName = stockmanName;
    }
}
