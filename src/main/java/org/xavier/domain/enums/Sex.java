package org.xavier.domain.enums;

/**
 * 描述信息：<br/>
 *
 * @author Xavier
 * @version 1.0
 * @date 2020/6/9
 * @since Jdk 1.8
 */
public enum Sex {
    /**
     * 男
     */
    man(0, "man"),
    /**
     * 女
     */
    woman(1, "women");
    private int index;
    private String value;

    Sex(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}