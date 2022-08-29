package com.base.demo.task;

import com.base.common.utils.DateUtils;
import com.base.common.utils.StringUtils;
import com.base.demo.constant.enums.BusinessCronEnum;
import com.base.demo.mapper.DemoMapper;
import com.base.demo.service.intf.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Single Minded
 * @create 2022-06-04 12:56:11
 * @Description 唐山网证（正常申报，入唐卡口）定时任务
 * @since 1.0
 */
@Slf4j
@Configuration
public class DemoScheduleTask implements SchedulingConfigurer {

    @Resource
    DemoService demoService;
    @Resource
    DemoMapper demoMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
                    log.info("《{}》开始执行定时任务", DateUtils.now());
                    demoService.task();
                    log.info("《{}》结束执行定时任务", DateUtils.now());
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    String cron = demoMapper.selectCron(BusinessCronEnum.NETWORK_DECLARE.getValue());
                    Date date;
                    if (StringUtils.isBlank(cron)) {
                        date = new CronTrigger("0 0 13 * * ?").nextExecutionTime(triggerContext);
                    } else {
                        date = new CronTrigger(cron).nextExecutionTime(triggerContext);
                    }
                    return date;
                }
        );
    }
}
