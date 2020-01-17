package com.john.scheduler.job;

import java.time.LocalDate;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        log.error("Hello World! - [{}]", LocalDate.now());
    }

}
