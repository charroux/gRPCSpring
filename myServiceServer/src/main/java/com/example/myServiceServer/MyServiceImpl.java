package com.example.myServiceServer;

import com.example.lib.HelloReply;
import com.example.lib.HelloRequest;
import com.example.lib.MyServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {

    Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        logger.info("sayHello => " + request);
        responseObserver.onNext(HelloReply.newBuilder()
                .setMessage("Hello " + request.getName())
                .build());
        responseObserver.onCompleted();
    }
}
