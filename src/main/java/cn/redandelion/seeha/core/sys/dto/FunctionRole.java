package cn.redandelion.seeha.core.sys.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.Table;

@Table(name = "sys_role_function")
public class FunctionRole extends BaseDto {

//    关联表id
    private Long srfId;
//    角色Id
    private Long roleId;
//    g功能ID
    private Long functionId;

    public Long getSrfId() {
        return srfId;
    }

    public void setSrfId(Long srfId) {
        this.srfId = srfId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }
}
