package io.pw.response;

import com.sun.net.httpserver.HttpExchange;

public interface ResponseHandler {

    static ResponseHandler ofPath(HttpExchange request) {
        String path = request.getRequestURI().getPath();
        if ("/".equals(path)) {
            return new MainPageHandler();
        } else if ("/api/products".equals(path)) {
            request.getResponseHeaders().set("Content-Type", "application/json");
            final String requestMethod = request.getRequestMethod();
            if ("GET".equals(requestMethod)) {
                return new GetApiHandler();
            } else {
                return new PostApiHandler(request.getRequestBody());
            }
        } else if("/add".equals(path)) {
            final String requestMethod = request.getRequestMethod();
            if ("GET".equals(requestMethod)) {
                return new AddProductFormHandler();
            } else {
                return new StoreNewProductHandler(request.getRequestBody());
            }
        } else {
            return () -> new byte[]{};
        }
    }

    byte[] handle();

}
