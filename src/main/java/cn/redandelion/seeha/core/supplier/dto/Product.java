package cn.redandelion.seeha.core.supplier.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name="PRODUCT")
@Entity
public class Product extends BaseDto {
    /**
     *     产品 ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long productId;
    /**
     *     供应商 ID
     */
    private Long supplierId;
    /**
     *     产品类型
     */
    private String productType;
    /**
     *     产品名称
     */
    private String productName;

    /**
     *     产品成本价格
     */

    private BigDecimal price;

    /**
     *     产品 销售价格
     */
    private BigDecimal totalPrice;
    /**
     *     产品 描述
     */
    @Transient
    private int number;

    private String productOtherDesc;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductOtherDesc() {
        return productOtherDesc;
    }

    public void setProductOtherDesc(String productOtherDesc) {
        this.productOtherDesc = productOtherDesc;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
