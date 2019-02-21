package cn.redandelion.seeha.core.supplier.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Table(name="SUPPLIER")
@Entity
public class Supplier extends BaseDto {
/**
 *     供应商Id
  */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long supplierId;
/**
 *     供应商code
 *
 */
    @NotEmpty
    private String supplierCode;
/**
 *     供应商 名称
 *
 */
    @NotEmpty
    private String fullName;
/**
 *     供应商 英文名
 *
 */
    private String englishName;
    /**
     *     供应商 信用码
     *
     */
    private String creditCode;
    /**
     *     供应商 税号
     *
     */
    private String dutyParagraph;
    /**
     *     供应商 资产
     *
     */
    private String supplierAmount;
    /**
     *     父供应商
     *
     */
    private String parentCustomersId;
    /**
     *     供应商 国家
     *
     */
    private String addressCountry;
    /**
     *     供应商 省
     *
     */
    private String addressProvince;
    /**
     *     供应商 城市
     *
     */
    private String addressCity;
    /**
     *     供应商 地区
     *
     */
    private String addressZone;
    /**
     *     供应商 详细地址
     *
     */
    private String addressDetails;
    /**
     *     供应商 电话
     *
     */
    private String phone;
    /**
     *     供应商 网站
     *
     */
    private String website;
    /**
     *     供应商 是否上市
     *
     */
    private String isListed;
    /**
     *     供应商 其他描述
     *
     */
    private String otherDescription;
    /**
     *     供应商  产品
     *
     */

    @Transient
    private List<Product> productList;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getDutyParagraph() {
        return dutyParagraph;
    }

    public void setDutyParagraph(String dutyParagraph) {
        this.dutyParagraph = dutyParagraph;
    }

    public String getSupplierAmount() {
        return supplierAmount;
    }

    public void setSupplierAmount(String supplierAmount) {
        this.supplierAmount = supplierAmount;
    }

    public String getParentCustomersId() {
        return parentCustomersId;
    }

    public void setParentCustomersId(String parentCustomersId) {
        this.parentCustomersId = parentCustomersId;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressZone() {
        return addressZone;
    }

    public void setAddressZone(String addressZone) {
        this.addressZone = addressZone;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIsListed() {
        return isListed;
    }

    public void setIsListed(String isListed) {
        this.isListed = isListed;
    }

    public String getOtherDescription() {
        return otherDescription;
    }

    public void setOtherDescription(String otherDescription) {
        this.otherDescription = otherDescription;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
