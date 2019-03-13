package cn.redandelion.seeha.core.inventory.dto;

import java.io.Serializable;

public class ChatsOfStore implements Serializable {
    /**
     *
      */
    private Integer total;

    private String inventoryName;

    private String productName;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
