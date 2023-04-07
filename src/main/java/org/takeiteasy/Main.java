package org.takeiteasy;

import com.sun.net.httpserver.HttpHandler;
import org.takeiteasy.application.ApplicationImpl;
import org.takeiteasy.application.ContextImpl;
import org.takeiteasy.application.Handler;

public class Main {

    static final HttpHandler handler = exchange -> {
        try {
            var handler = new ContextImpl(Handler.valueOf(exchange.getRequestMethod()));
            handler.handleRequest(exchange);
        } catch (IllegalArgumentException illegalArgumentException) {
            new ContextImpl(Handler.NOT_SUCH_A_METHOD).handleRequest(exchange);
        }
    };

    public static void main(String[] args) {
        ApplicationImpl application = ApplicationImpl.getInstance();
        application.setRoute("/", handler);
        application.run();
    }
}