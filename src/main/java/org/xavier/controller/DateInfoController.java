package org.xavier.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xavier.dao.DateInfoMapper;
import org.xavier.domain.po.DateInfo;

@RestController
public class DateInfoController {
    @Autowired
    DateInfoMapper dateInfoMapper;

    @PostMapping(value = "/dateInfo")
    public Object addDateInfo(@RequestBody DateInfo dateInfo) {
        int affectRow = dateInfoMapper.insert(dateInfo);
        return affectRow;
    }

    @GetMapping(value = "/dateInfo/{id}")
    public Object queryDateInfoById(@PathVariable("id") Integer id) {
        QueryWrapper<DateInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        DateInfo dateInfo = dateInfoMapper.selectOne(queryWrapper);
        return dateInfo;
    }
}