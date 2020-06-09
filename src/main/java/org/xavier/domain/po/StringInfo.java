package org.xavier.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.xavier.domain.enums.Sex;

public class StringInfo {
    /**
     * 主键自增列
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String defaultChar;
    private String variableLengthChar;
    private Sex sex;
    private String mySet;
    private byte[] file;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefaultChar() {
        return defaultChar;
    }

    public void setDefaultChar(String defaultChar) {
        this.defaultChar = defaultChar;
    }

    public String getVariableLengthChar() {
        return variableLengthChar;
    }

    public void setVariableLengthChar(String variableLengthChar) {
        this.variableLengthChar = variableLengthChar;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getMySet() {
        return mySet;
    }

    public void setMySet(String mySet) {
        this.mySet = mySet;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
