package com.john.scheduler.config;

import com.john.scheduler.job.HelloJob;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {

    /**
     * 1. 创建Job对象
     */
    @Bean("jobDetailFactoryBean")
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(HelloJob.class);
        return factory;

    }

    /**
     * 2. 创建Trigger对象
     */
    @Bean("simpleTriggerFactoryBean")
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(@Qualifier("jobDetailFactoryBean") JobDetailFactoryBean jobDetailFactoryBean){
        SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
        factory.setJobDetail(jobDetailFactoryBean.getObject());
        factory.setRepeatInterval(2000);
        factory.setRepeatCount(10);
        return factory;

    }

    @Bean("cronTriggerFactoryBean")
    public CronTriggerFactoryBean cronTriggerFactoryBean(@Qualifier("jobDetailFactoryBean") JobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(jobDetailFactoryBean().getObject());
        factory.setCronExpression("0/5 * * * * ?");
        return factory;

    }

    /**
     * 3. 创建Scheduler对象
     */

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("cronTriggerFactoryBean") CronTriggerFactoryBean cronTriggerFactoryBean){
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setTriggers(cronTriggerFactoryBean.getObject());
        return factory;
    }
}
