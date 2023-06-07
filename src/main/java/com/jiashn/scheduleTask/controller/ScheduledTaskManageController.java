package com.jiashn.scheduleTask.controller;

import com.jiashn.scheduleTask.domain.ScheduleTask;
import com.jiashn.scheduleTask.service.ScheduledTaskManageService;
import com.jiashn.scheduleTask.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: jiangjs
 * @description: 定时任务管理类
 * @date: 2023/1/12 10:35
 **/
@RestController
@RequestMapping("/scheduled")
public class ScheduledTaskManageController {

    @Autowired
    private ScheduledTaskManageService manageService;

    @PostMapping("/addTask.do")
    public ResultUtil<?> addTask(@RequestBody @Validated ScheduleTask task){
        return manageService.addTask(task);
    }

    @PutMapping("/editTask.do")
    public ResultUtil<?> editTask(@RequestBody @Validated(value = ScheduleTask.Update.class) ScheduleTask task){
        return manageService.editTask(task);
    }

    @GetMapping("/deleteTask.do/{id}")
    public ResultUtil<?> deleteTask(@PathVariable("id") Integer id){
        return manageService.deleteTask(id);
    }
}