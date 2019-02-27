package cn.redandelion.seeha.core.sys.basic.dto;

import java.io.Serializable;

public class Code implements Serializable {

    private String meaning;
    private Long value;

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
