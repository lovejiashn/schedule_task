package com.jiashn.scheduleTask.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/6/7 11:08
 **/
@TableName("t_schedule_task")
@Data
public class ScheduleTask {
    public interface Update{};

    @TableId(type = IdType.AUTO)
    @NotNull(message = "任务id不能为空",groups = Update.class)
    private Integer id;

    @NotBlank(message = "请填写任务执行类")
    private String taskClazz;
    @NotBlank(message = "请填写任务执行方法")
    private String taskMethod;
    @NotBlank(message = "请填写任务执行时间，采用cron格式")
    private String cron;

    @TableLogic
    private Integer status;
}
