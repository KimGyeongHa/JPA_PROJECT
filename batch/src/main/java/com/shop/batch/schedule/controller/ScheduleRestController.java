package com.shop.batch.schedule.controller;

import com.shop.batch.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleRestController {

    private final ScheduleService scheduleService;

    @RequestMapping("/call")
    public String jobCall(){
        scheduleService.start();
        return "성공";
    }

    @RequestMapping("/cancel")
    public String jobCancel(@RequestParam("name") String jobName){
        return scheduleService.cancel(jobName);
    }

}
