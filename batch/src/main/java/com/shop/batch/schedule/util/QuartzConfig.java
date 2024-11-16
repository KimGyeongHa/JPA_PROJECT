package com.shop.batch.schedule.util;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
public class QuartzConfig {

    public SchedulerFactoryBean schedulerFactoryBean(AutowireCapableBeanFactory beanFactory) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        // Spring의 빈을 Quartz의 Job에 자동으로 주입하도록 설정
        schedulerFactoryBean.setJobFactory(new SpringBeanJobFactory());

        return schedulerFactoryBean;
    }
}
