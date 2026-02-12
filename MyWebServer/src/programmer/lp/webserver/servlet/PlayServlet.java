package programmer.lp.webserver.servlet;

import programmer.lp.webserver.server.Request;
import programmer.lp.webserver.server.Response;

public class PlayServlet extends BaseServlet {

    @Override
    public void service(Request request, Response response) {
        response.println("<html>")
                .println("<head>")
                .println("<meta charset=\"UTF-8\">")
                .println("<title>")
                .println("play")
                .println("</title>")
                .println("</head>")
                .println("<body>")
                .println("哈哈哈哈")
                .println("</body>")
                .println("</html>");
    }

}
