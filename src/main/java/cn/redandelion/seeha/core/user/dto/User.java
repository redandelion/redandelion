package cn.redandelion.seeha.core.user.dto;


import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;


@Table(name="SYS_USER")
@Entity
public class User extends BaseDto {

    @Id
    @GeneratedValue(generator="JDBC")
    private Long userId; //表ID，主键，供其他表做外键

    @NotEmpty(message = "用户名不能为空！")
    @Length(max = 50)
    private String userName;
//    密码
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6,max = 16,message = "密码长度6-16位！")
    private String userPassword;

    @Length(max = 150)
    private String email; //邮箱地址


    private String location;

    private String phone;

    private Date startTime;

    private Date endTime;

    private String description;

    private String status;
//    员工Id
    private String employeeId;
//    客户Id
    private String customerId;
//    供应商Id
    private String supplier;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
