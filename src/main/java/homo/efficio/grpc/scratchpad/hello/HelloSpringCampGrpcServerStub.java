package homo.efficio.grpc.scratchpad.hello;

import io.grpc.stub.StreamObserver;

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
}
