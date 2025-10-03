package com.example.myServiceClient.service;

import com.example.lib.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    Logger logger = LoggerFactory.getLogger(MyService.class);

    @GrpcClient("myService")
    private MyServiceGrpc.MyServiceStub myServiceStub;
    //private MyServiceGrpc.MyServiceBlockingStub myServiceStub;

    /*
    public String sayHello(String to){
        logger.info(to);
        HelloReply reply = myServiceStub.sayHello(HelloRequest.newBuilder().setName(to).build());
        return reply.getMessage();
    }
    */

    class InvoiceObserver extends Thread implements StreamObserver<Invoice> {

        Logger logger = LoggerFactory.getLogger(InvoiceObserver.class);

        @Override
        public void onNext(Invoice invoice) {
            logger.info("Invoice: " + invoice.getAmount());
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {

        }
    }

    class HelloReply extends Thread implements StreamObserver<HelloReply> {

        @Override
        public void onNext(HelloReply value) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {

        }
    }

    public void sayHello(String to){
        logger.info(to);
        /*HelloReply helloReply = new HelloReply();
        helloReply.start();
        myServiceStub.sayHello(HelloRequest.newBuilder().setName(to).build(), helloReply);*/
    }

    public void addCar(String plateNumber) {
        logger.info(plateNumber);
        InvoiceObserver invoiceObserver = new InvoiceObserver();
        invoiceObserver.start();
        StreamObserver<Car> carStreamObserver = myServiceStub.rent(invoiceObserver);
        carStreamObserver.onNext(Car.newBuilder().setPlateNumber(plateNumber).build());
        carStreamObserver.onNext(Car.newBuilder().setPlateNumber(plateNumber).build());
        carStreamObserver.onCompleted();
    }


}
