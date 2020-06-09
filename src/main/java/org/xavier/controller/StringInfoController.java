package org.xavier.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xavier.dao.StringInfoMapper;
import org.xavier.domain.enums.Sex;
import org.xavier.domain.po.StringInfo;

import java.io.IOException;

@RestController
public class StringInfoController {
    @Autowired
    StringInfoMapper stringInfoMapper;

    @PostMapping(value = "/stringInfo")
    public Object addStringInfo(@RequestParam("file") MultipartFile file,
                              @RequestParam(value = "defaultChar", required = false, defaultValue = "null") String defaultChar,
                              @RequestParam(value = "variableLengthChar", required = false, defaultValue = "null") String variableLengthChar,
                              @RequestParam(value = "sex", required = false, defaultValue = "man") Sex sex) throws IOException {
        StringInfo stringInfo = new StringInfo();
        stringInfo.setSex(sex);
        if (!defaultChar.equals("null")) {
            stringInfo.setDefaultChar(defaultChar);
        }
        if (!variableLengthChar.equals("null")) {
            stringInfo.setVariableLengthChar(variableLengthChar);
        }
        stringInfo.setFile(file.getBytes());
        int affectRow = stringInfoMapper.insert(stringInfo);
        return affectRow;
    }

    @GetMapping(value = "/stringInfo/{id}")
    public Object queryStringInfoById(@PathVariable("id") Integer id) {
        QueryWrapper<StringInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        StringInfo stringInfo = stringInfoMapper.selectOne(queryWrapper);
        return stringInfo;
    }

    @DeleteMapping(value = "/stringInfo/{id}")
    public Object deleteStringInfoById(@PathVariable("id") Integer id) {
        QueryWrapper<StringInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int affectRow = stringInfoMapper.delete(queryWrapper);
        return affectRow;
    }
}