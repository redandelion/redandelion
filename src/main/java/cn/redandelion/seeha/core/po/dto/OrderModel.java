package cn.redandelion.seeha.core.po.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "ORDER_MODEL")
@Entity
public class OrderModel extends BaseDto {
    /**
     * 订单 id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long orderId;
    /**
     * 订单 编号
     */
    @NotEmpty
    private String orderNum;
    /**
     * 订单 创建人
     */
    private Long creater;

    /**
     * 订单 时间
     */
    private Date createTime;
    /**
     * 订单 审批人
     */
    private Long checker;
    /**
     * 订单 审批时间
     */
    private Date checkTime;
    /**
     * 订单 完成人
     */
    private Long completer;
    /**
     * 订单 完成时间
     */
    private Date endTime;
    /**
     * 订单 类型
     */
    private Integer orderType;
    /**
     * 订单 状态
     */
    private Integer orderState;
    /**
     * 订单 明细数量
     */
    private Integer totalNum;
    /**
     * 订单 明细总额
     */
    private BigDecimal totalPrice;
    /**
     * 订单 供应商Id
     */
    private Long supplierId;
    /**
     * 订单 创建人姓名
     */
    @Transient
    private String createrName;
    /**
     * 订单 审核人姓名
     */
    @Transient
    private String checkerName;
    /**
     * 订单 完结人姓名
     */
    @Transient
    private String completerName;
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getChecker() {
        return checker;
    }

    public void setChecker(Long checker) {
        this.checker = checker;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Long getCompleter() {
        return completer;
    }

    public void setCompleter(Long completer) {
        this.completer = completer;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public String getCompleterName() {
        return completerName;
    }

    public void setCompleterName(String completerName) {
        this.completerName = completerName;
    }
}
