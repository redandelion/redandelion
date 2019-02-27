package cn.redandelion.seeha.core.po.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_DETAIL")
public class OrderDetail extends BaseDto {
    /**
     * 订单详情 Id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long orderDetailId;
    /**
     * 订单详情  数量
     */
    private Integer detailNum;
    /**
     * 订单详情  价格
     */
    private BigDecimal detailPrice;
    /**
     * 订单详情  产品Id
     */
    private Long productId;
    /**
     * 订单详情  订单Id
     */
    private Long orderId;
    /**
     * 订单详情  订单Id
     */
    private Long surplus;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailid) {
        this.orderDetailId = orderDetailid;
    }

    public Integer getDetailNum() {
        return detailNum;
    }

    public void setDetailNum(int detailNum) {
        this.detailNum = detailNum;
    }

    public BigDecimal getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(BigDecimal detailPrice) {
        this.detailPrice = detailPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSurplus() {
        return surplus;
    }

    public void setSurplus(Long surplus) {
        this.surplus = surplus;
    }
}
