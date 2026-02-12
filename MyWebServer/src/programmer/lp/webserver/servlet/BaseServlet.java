package programmer.lp.webserver.servlet;

import programmer.lp.webserver.server.Request;
import programmer.lp.webserver.server.Response;

import java.lang.reflect.Method;

public class BaseServlet implements Servlet {

    @Override
    public void service(Request request, Response response) {
        String uri = request.getUri();
        if (uri.endsWith("/")) uri = uri.substring(0, uri.length() - 1);
        final String[] split = uri.split("/");
        try {
            final String methodName = split[split.length - 1];
            if (split.length >= 3) {
                final Method method = getClass().getMethod(methodName, Request.class, Response.class);
                method.invoke(this, request, response);
            } else if (split.length == 2) {
                final Method method = getClass().getMethod("admin", Request.class, Response.class);
                method.invoke(this, request, response);
            } else {
                // go to index.jsp
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
