package com.example.web;

import com.example.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final MyLogger logger;
    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {

        System.out.println("myLogger = " + logger.getClass());
        logger.setRequestURL(String.valueOf(request.getRequestURL()));
        logger.log(" controller test");
        logDemoService.logic(" testId");

        return "OK";
    }
}
