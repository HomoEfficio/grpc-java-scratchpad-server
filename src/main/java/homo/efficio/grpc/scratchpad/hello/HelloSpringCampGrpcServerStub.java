package homo.efficio.grpc.scratchpad.hello;

import io.grpc.stub.StreamObserver;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-04.
 */
public class HelloSpringCampGrpcServerStub extends HelloSpringCampGrpc.HelloSpringCampImplBase {

    private final Logger logger = Logger.getLogger(HelloSpringCampGrpcServerStub.class.getName());

    @Override
    public void unaryHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        logger.info("Unary 메시지 왔다: " + request.getClientName());
        HelloResponse helloResponse = HelloResponse.newBuilder().setWelcomeMessage("Unary Hello " + request.getClientName()).build();
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<HelloRequest> clientStreamingHello(StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            StringBuilder sb = new StringBuilder();
            @Override
            public void onNext(HelloRequest request) {
                logger.info("Client Streaming 메시지 왔다: " + request.getClientName());
                sb.append(request.getClientName())
                  .append("\n======================\n");
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.SEVERE, "Client Streaming requestObserver.onError() 호출");
            }

            @Override
            public void onCompleted() {
                String welcomeMessage = sb.toString();
                logger.info("Client Streaming 회신한다:\n" + welcomeMessage);
                responseObserver.onNext(
                        HelloResponse.newBuilder()
                                     .setWelcomeMessage(welcomeMessage)
                                     .build()
                );
                responseObserver.onCompleted();
            }
        };
    }
}