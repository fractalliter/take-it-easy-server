package org.takeiteasy.application;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationImpl implements Application {
    static final String APPLICATION_PROPERTIES = "application.properties";
    private static ApplicationImpl instance = null;
    private static final Object mutex = new Object();

    Map<String, HttpHandler> routes = new HashMap<>();

    private ApplicationImpl(){}

    public static ApplicationImpl getInstance(){
        ApplicationImpl result = instance;
        synchronized (mutex){
            if (result == null)
                result = instance = new ApplicationImpl();
            else result = instance;
        }
        return result;
    }

    public Boolean setRoute(String path, HttpHandler handler) {
        if(this.routes.putIfAbsent(path, handler)!=null)
            throw new RuntimeException(path + " already exist");
        else return true;
    }

    @Override
    public void run() {
        try {
            Properties props = loadProperties();
            String serverPort = props.getProperty("server-port");
            String backlog = props.getProperty("server-backlog");

            ExecutorService executor = Executors.newFixedThreadPool(
                    Integer.parseInt(props.getProperty("thread-pool-size"))
            );

            HttpServer server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(serverPort)),
                    Integer.parseInt(backlog)
            );
            for (Map.Entry<String, HttpHandler> route: routes.entrySet())
                server.createContext(route.getKey(), route.getValue());
            server.setExecutor(executor);
            server.start();
            System.out.println("Server started on port " + serverPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties loadProperties() throws IOException {
        Properties props = new Properties();
        props.load(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(APPLICATION_PROPERTIES)
        );
        return props;
    }
}
