package cn.redandelion.seeha.core.sys.basic.dto;

import cn.redandelion.seeha.core.annotation.Condition;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class BaseDto implements Serializable {

//    who 字段

    @JsonIgnore
    @Column(updatable = false)
    @Condition(exclude = true)
    private Long createdBy;

    @JsonIgnore
    @Column(updatable = false)
    @Condition(exclude=true)
    private Date creationDate;

    @JsonIgnore
    @Column
    @Condition(exclude=true)
    private Long lastUpdatedBy;

    @JsonIgnore
    @Column
    @Condition(exclude=true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;

    @JsonIgnore
    @Column
    @Condition(exclude=true)

    private Long lastUpdateLogin;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Long lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }


}
