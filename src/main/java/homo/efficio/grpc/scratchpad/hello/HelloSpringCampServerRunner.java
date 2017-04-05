package homo.efficio.grpc.scratchpad.hello;

import io.grpc.Server;

import java.io.IOException;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-04.
 */
public class HelloSpringCampServerRunner {

    public static void main(String[] args) throws IOException, InterruptedException {

        final int port = 54321;

        Server server = new HelloSpringCampGrpcServer(port).getServer();

        server.awaitTermination();

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    System.err.println("gRPC 종료...");
                    server.shutdown();
                    System.err.println("gRPC 종료 완료");
                })
        );
    }
}
