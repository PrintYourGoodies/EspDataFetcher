package com.mkyong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "hello";

    }

    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        model.addObject("msg", name);
        System.out.println("Time:"+ LocalDateTime.now()+"\tValue recived: " + name);
        return model;

    }
    //http://192.168.1.29:8080/spring3/dataCollector?temp=100&hum=15
    @RequestMapping(value = "/dataCollector", method = RequestMethod.GET)
    @ResponseBody
    public String getWeatherConditionsFromRequestParams(
            @RequestParam("hum") float hum,@RequestParam("temp") float temp) {
        LocalDateTime roundFloor =  LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        System.out.println("Time:"+ roundFloor+"\tValue recived: Temp= "+temp+" C Humidity= "+hum);
        return "";
    }
}