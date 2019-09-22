package com.yp.rpc.foo;

import com.yp.rpc.server.Server;
import com.yp.rpc.server.spring.ServerContainor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SampleConfiguration {

    public static final String SERVER_ADDRESS = "localhost:12384";

    @Bean
    public ServerContainor rpcContainor() {
        return new ServerContainor();
    }

    @Bean
    public Server server(ServerContainor containor) {
        return new Server(SERVER_ADDRESS, containor);
    }
}
