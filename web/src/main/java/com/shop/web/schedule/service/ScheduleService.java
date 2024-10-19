package com.shop.web.schedule.service;


import com.shop.web.schedule.util.job.MessageScheduled;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final MessageScheduled scheduled;

    public void start()  {
        scheduled.scheduleStart();
    }

    public String cancel(String jobName){
        return scheduled.cancelTargetSchedule(jobName);
    }

}
