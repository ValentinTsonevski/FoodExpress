package com.example.foodexpress.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkingTimeController {

    @GetMapping("/working-time")
    public String workingTime(){

        return "working-time";
    }


}
