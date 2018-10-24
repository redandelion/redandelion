package cn.redandelion.seeha.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * redandelion
 */
public class ResponseData {
    // 返回状态编码
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    // 返回信息
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    //数据
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<?> rows;

    // 成功标识
    private boolean success = true;

    //总数
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;
    public ResponseData() {
    }

    public ResponseData(boolean success) {
        setSuccess(success);
    }

    public ResponseData(List<?> list) {
        this(true);
        setRows(list);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
        if (rows instanceof Page) {
            setTotal(((Page<?>) rows).getTotal());
        } else {
            setTotal((long) rows.size());
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
