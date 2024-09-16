package com.example.myServiceClient.web;

import com.example.myServiceClient.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebService {

    MyService myService;

    @Autowired
    public WebService(MyService myService){
        this.myService = myService;
    }

    @GetMapping("/{to}")
    @ResponseStatus(HttpStatus.OK)
    public String sayHello(@PathVariable("to") String to){
        return myService.sayHello(to);
    }

}
