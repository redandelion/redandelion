package cn.redandelion.seeha.core.sys.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;


@Table(name = "sys_function_menu")
public class Function extends BaseDto {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long functionId;

    private String moduleCode;

    private String functionIcon;
    @NotEmpty
    private String functionCode;

    private String functionName;

    private String functionDescription;

    private Long resourceId;

    private String type;

    private Long parentFunctionId;

    private String enabledFlag;

    private BigDecimal functionSequence;

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getFunctionIcon() {
        return functionIcon;
    }

    public void setFunctionIcon(String functionIcon) {
        this.functionIcon = functionIcon;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getParentFunctionId() {
        return parentFunctionId;
    }

    public void setParentFunctionId(Long parentFunctionId) {
        this.parentFunctionId = parentFunctionId;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enableFlag) {
        this.enabledFlag = enableFlag;
    }

    public BigDecimal getFunctionSequence() {
        return functionSequence;
    }

    public void setFunctionSequence(BigDecimal functionSequence) {
        this.functionSequence = functionSequence;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    @Override
    public String toString() {
        return "Function{" +
                "functionId=" + functionId +
                ", moduleCode='" + moduleCode + '\'' +
                ", functionIcon='" + functionIcon + '\'' +
                ", functionCode='" + functionCode + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionDescription='" + functionDescription + '\'' +
                ", resourceId=" + resourceId +
                ", type='" + type + '\'' +
                ", parentFunctionId=" + parentFunctionId +
                ", enabledFlag='" + enabledFlag + '\'' +
                ", functionSequence=" + functionSequence +
                '}';
    }
}
