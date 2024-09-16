# gRPC with Spring boot

This project in composed of a project defining the service contract (according to Protocol Buffers): https://github.com/charroux/gRPCSpring/tree/main/myServiceInterface

And two subprojects:
- one for the service definition: https://github.com/charroux/gRPCSpring/tree/main/myServiceServer
- the other one for the client: https://github.com/charroux/gRPCSpring/tree/main/myServiceClient

https://github.com/charroux/gRPCSpring/blob/main/settings.gradle set the whole project.

## Requirements

Java 17.

## The Server side

Service definition: https://github.com/charroux/gRPCSpring/blob/main/myServiceInterface/src/main/proto/myService.proto

Project settings: https://github.com/charroux/gRPCSpring/blob/main/myServiceInterface/settings.gradle

build.gradle file: https://github.com/charroux/gRPCSpring/blob/main/myServiceInterface/build.gradle

## The servder implementation

The implementation: https://github.com/charroux/gRPCSpring/blob/main/myServiceServer/src/main/java/com/example/myServiceServer/MyServiceImpl.java

Notice the annotation:
```
@GrpcService
```

Project settings: https://github.com/charroux/gRPCSpring/blob/main/myServiceServer/settings.gradle

build.gradle file: https://github.com/charroux/gRPCSpring/blob/main/myServiceServer/build.gradle

See how the interface definition project is included:
```
implementation project(':myServiceInterface')
```
Server port selection: https://github.com/charroux/gRPCSpring/blob/main/myServiceServer/src/main/resources/application.properties

## The client side

A Rest controller: https://github.com/charroux/gRPCSpring/tree/main/myServiceClient/src/main/java/com/example/myServiceClient/web

Using a service class invoquing the gRPC service: https://github.com/charroux/gRPCSpring/blob/main/myServiceClient/src/main/java/com/example/myServiceClient/service/MyService.java

Notice the annotation:
```
@GrpcClient("myService")
```
And its corresponding definition: https://github.com/charroux/gRPCSpring/blob/main/myServiceClient/src/main/resources/application.properties

Notice also the server address:
```
grpc.client.myService.address=localhost:9090
```

## Build the whole project

In the root folder (gRPCSpring): 
```
./gradlew build
```
See the generated files for the service: https://github.com/charroux/gRPCSpring/tree/main/myServiceInterface/src/generated/main

## Launch the server side
In the server folder (myServiceServer):
```
java -jar build/libs/myServiceServer-0.0.1-SNAPSHOT.jar
```

## Launch the client side
In the client folder (myServiceClient):
```
java -jar build/libs/myServiceClient-0.0.1-SNAPSHOT.jar
```
Service remote access: http://localhost:8080/world

