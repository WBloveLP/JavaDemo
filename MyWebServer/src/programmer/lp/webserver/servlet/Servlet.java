package programmer.lp.webserver.servlet;

import programmer.lp.webserver.server.Request;
import programmer.lp.webserver.server.Response;

public interface Servlet {

    void service(Request request, Response response);

}
