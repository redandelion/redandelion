package cn.redandelion.seeha.core.supplier.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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

    private Double price;

    /**
     *     产品 销售价格
     */
    private Double totalPrice;
    /**
     *     产品 描述
     */
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductOtherDesc() {
        return productOtherDesc;
    }

    public void setProductOtherDesc(String productOtherDesc) {
        this.productOtherDesc = productOtherDesc;
    }
}
