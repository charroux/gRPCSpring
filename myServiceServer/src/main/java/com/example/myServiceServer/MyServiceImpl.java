package com.example.myServiceServer;

import com.example.lib.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService

public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {

    Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);
    int amount;

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        logger.info("sayHello => " + request);
        responseObserver.onNext(HelloReply.newBuilder()
                .setMessage("Hello " + request.getName())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Car> rent(
            StreamObserver<Invoice> responseObserver){

        return new StreamObserver<Car>() {

            Logger logger = LoggerFactory.getLogger(StreamObserver.class);

            @Override
            public void onNext(Car car) {
                logger.info("Car received: " + car.getPlateNumber());
                amount = amount + 1000;
                responseObserver.onNext(Invoice.newBuilder().setAmount(amount).build());
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {
                logger.info("On completed");
                responseObserver.onCompleted();
            }
        };

    }

}
