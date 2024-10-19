package com.shop.web.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shop.web.schedule.service.ScheduleService;

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
