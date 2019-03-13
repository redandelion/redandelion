package cn.redandelion.seeha.core.inventory.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "STORE_LOG")
public class StoreLog extends BaseDto {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long logId;
    /**
     * 操作人
     */
    private Long empId;
    /**
     * 订单详情id
     */
    private Long orderDetailId;
    /**
     * 仓库id
     */
    private Long storeId;
    /**
     * 仓库记录 操作时间
     */
    private Date operTime;
    /**
     * 仓库记录 操作数量
     */
    private Integer num;
    /**
     * 仓库记录 操作类型 0 为入库，1为出库
     */
    private Integer type;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return storeId.toString()+ type.toString();
    }
}
