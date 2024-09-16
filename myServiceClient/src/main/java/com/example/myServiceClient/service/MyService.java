package com.example.myServiceClient.service;

import com.example.lib.HelloReply;
import com.example.lib.HelloRequest;
import com.example.lib.MyServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    Logger logger = LoggerFactory.getLogger(MyService.class);

    @GrpcClient("myService")
    private MyServiceGrpc.MyServiceBlockingStub myServiceStub;

    public String sayHello(String to){
        logger.info(to);
        HelloReply reply = myServiceStub.sayHello(HelloRequest.newBuilder().setName(to).build());
        return reply.getMessage();
    }
}
