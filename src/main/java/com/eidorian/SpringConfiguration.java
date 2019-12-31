package com.eidorian;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SpringConfiguration {

    // @Bean
    // public TaskExecutor taskExecutor() {
    //     ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    //     taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
    //     return taskExecutor();
    // }
}