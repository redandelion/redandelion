package cn.redandelion.seeha.core.sys.function.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class MenuItem implements Comparable<MenuItem>,Serializable{

    private List<MenuItem> children;

    private boolean expand = false;

    private String functionCode;

    private String icon;

    private Long id;

    private Boolean ischecked;

    @JsonIgnore
    private MenuItem parent;

    private long score;

    private Long shortcutId;

    private String text;

    private String url;
    @Override
    public int compareTo(MenuItem o) {
        return (int) (this.score - o.score);
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItem> children) {
        this.children = children;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIschecked() {
        return ischecked;
    }

    public void setIschecked(Boolean ischecked) {
        this.ischecked = ischecked;
    }

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public Long getShortcutId() {
        return shortcutId;
    }

    public void setShortcutId(Long shortcutId) {
        this.shortcutId = shortcutId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
