package cn.redandelion.seeha.core.util;

public enum SysConstants {
    PAGESTART(1,"分页开始标记"),
    PAGEEND(10,"分页大小")
    ;

    private int value;
    private String desc;

    SysConstants(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
