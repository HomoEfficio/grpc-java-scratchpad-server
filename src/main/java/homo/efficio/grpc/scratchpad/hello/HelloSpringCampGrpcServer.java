package homo.efficio.grpc.scratchpad.hello;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-04.
 */
public class HelloSpringCampGrpcServer {

    private final Logger logger = Logger.getLogger(HelloSpringCampGrpcServer.class.getName());

    private final Server server;

    public HelloSpringCampGrpcServer(int port) throws IOException {

        this.server = ServerBuilder.forPort(port)
                                   .addService(new HelloSpringCampGrpcServerStub())
                                   .build()
                                   .start();
        logger.info("gRPC 서버 시작!!! on " + port);
    }

    public Server getServer() {
        return server;
    }
}
