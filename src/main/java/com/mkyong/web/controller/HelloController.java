package com.mkyong.web.controller;

import com.mkyong.web.controller.dbUtils.dbUtils;
import org.h2.util.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTP;


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
        System.out.println("Time:" + LocalDateTime.now() + "\tValue recived: " + name);
        return model;

    }

    //http://192.168.1.29:8080/spring3/dataCollector?temp=100&hum=15
    @RequestMapping(value = "/dataCollector", method = RequestMethod.GET)
    @ResponseBody
    public String getWeatherConditionsFromRequestParams(
            @RequestParam("hum") float hum, @RequestParam("temp") float temp) {
        LocalDateTime roundFloor = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        dbUtils.test(hum, temp);
        System.out.println("Time:" + roundFloor + "\tValue recived: Temp= " + temp + " C Humidity= " + hum);
        return "";
    }

    @RequestMapping(value = "/dataFetcher", method = RequestMethod.GET)
    @ResponseBody
    public String getJson(  @RequestParam("json") String json) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
        System.out.println(json);
        return "";
    }
    @RequestMapping(value = "/postJson", method = RequestMethod.POST)
    @ResponseBody
    public String getJson( HttpServletRequest request,
                           HttpServletResponse response) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    request.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            String reqBody = URLDecoder.decode(sb.toString());
            System.out.println("Request Body:" + reqBody);
//            JSONObject json = new JSONObject(reqBody);
       int i=0;
        } catch (Exception e) {
            return null;
        }
        return "";
    }
}