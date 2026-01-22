package com.job.tracker.testController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connect")
public class connecting {
    private int count = 0;

    @GetMapping("/get")
    public String connected(){
        count++;
        return "Congrats on getting this information, now we can really get started :) " + count;
    }
}
