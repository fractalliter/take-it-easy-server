package org.takeiteasy;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try{
            var server = HttpServer.create(new InetSocketAddress(8081), 10);
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
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}