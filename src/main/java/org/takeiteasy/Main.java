package org.takeiteasy;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static final String APPLICATION_PROPERTIES = "application.properties";
    public static void main(String[] args) {
        try{
            Properties props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(APPLICATION_PROPERTIES));
            String serverPort = props.getProperty("server-port");
            ExecutorService executor = Executors.newFixedThreadPool(
                    Integer.parseInt(props.getProperty("thread-pool-size"))
            );
            var server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(serverPort)),
                    10
            );
            server.createContext("/", exchange -> {
                try {
                    var handler = new ServerContext(RequestMethod.valueOf(exchange.getRequestMethod()));
                    handler.handleRequest(exchange);
                }catch (IllegalArgumentException illegalArgumentException){
                    new ServerContext(RequestMethod.NOT_SUCH_A_METHOD).handleRequest(exchange);
                }
            });
            server.setExecutor(executor);
            server.start();
            System.out.println("Server started on port "+serverPort);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}