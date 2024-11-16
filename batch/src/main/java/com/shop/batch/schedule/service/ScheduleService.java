package com.shop.batch.schedule.service;


import com.shop.batch.schedule.util.quartz.Scheduled;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final Scheduled scheduled;

    public void start()  {
        scheduled.scheduleStart();
    }

    public String cancel(String jobName){
        return scheduled.cancelTargetSchedule(jobName);
    }

}
