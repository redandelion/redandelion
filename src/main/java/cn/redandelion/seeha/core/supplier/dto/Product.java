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
    private BigDecimal outPrice;
    /**
     *     产品 销售总价格
     */
    private BigDecimal totalPrice;
    /**
     *     产品 描述
     */
    private String productOtherDesc;
    /**
     *     产品 单位
     */
    private String unit;
    /**
     *     产品 生成者
     */
    private String producer;

    @Transient
    private int number;



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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public BigDecimal getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(BigDecimal outPrice) {
        this.outPrice = outPrice;
    }
}
