package com.example.web;

import com.example.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final ObjectProvider<MyLogger> provider;

    public void logic(String id) {
        MyLogger logger = provider.getObject();
        logger.log(" service id = " + id);
    }
}
