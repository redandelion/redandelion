package cn.redandelion.seeha.core.sys.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;
import org.springframework.security.core.parameters.P;

import javax.persistence.Table;

@Table(name = "sys_function_resource")
public class FunctionResource extends BaseDto {
//    id
    private Long functionSrcId;
//    功能Id
    private Long functionId;
//    资源Id
    private Long resourceId;

    public Long getFunctionSrcId() {
        return functionSrcId;
    }

    public void setFunctionSrcId(Long functionSrcId) {
        this.functionSrcId = functionSrcId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
