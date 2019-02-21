package cn.redandelion.seeha.core.sys.function.dto;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Table(name = "sys_resource")
public class Resource extends BaseDto {
//    资源ID
    @Id
    @GeneratedValue(generator = "JDBC")
    private  Long resourceId;
//    URL 资源访问相对地址
    @NotEmpty
    private String url;
//    TYPE 资源类型，分类管理
    private String name;

    private String type;
//    资源描述
    private String description;
//    需要登录?
    private String loginRequire;
//    访问检查
    private String access_check;
//

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoginRequire() {
        return loginRequire;
    }

    public void setLoginRequire(String loginRequire) {
        this.loginRequire = loginRequire;
    }

    public String getAccess_check() {
        return access_check;
    }

    public void setAccess_check(String access_check) {
        this.access_check = access_check;
    }
}
