package cn.redandelion.seeha.core.user.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_user_role")
public class UserRole extends BaseDto {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long surId;

    private Long userId;

    private Long roleId;

    public Long getSurId() {
        return surId;
    }

    public void setSurId(Long surId) {
        this.surId = surId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
