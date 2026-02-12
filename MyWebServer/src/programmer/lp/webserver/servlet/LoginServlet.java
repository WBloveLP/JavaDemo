package programmer.lp.webserver.servlet;

import programmer.lp.webserver.server.Request;
import programmer.lp.webserver.server.Response;

public class LoginServlet extends BaseServlet {

//    @Override
//    public void service(Request request, Response response) {
//
//        super.service(request, response);
//
////        拿到uri的path就可以通过反射来调用对应Servlet的方法了
////        System.out.println(request.getUri());
//
//        response.println("<html>")
//                .println("<head>")
//                .println("<meta charset=\"UTF-8\">")
//                .println("<title>")
//                .println("服务器响应成功")
//                .println("</title>")
//                .println("</head>")
//                .println("<body>")
//                .print("恭喜用户：")
////                    .println(Arrays.toString(request.getParameters("username")))
//                .println(request.getParameter("username"))
//                .println("登录成功！")
//                .println("</body>")
//                .println("</html>");
//    }

    public void login(Request request, Response response) {
        System.out.println("login");
    }

    public void register(Request request, Response response) {
        System.out.println("register");
    }

    public void admin(Request request, Response response) {
        System.out.println("admin");
    }

    public void front(Request request, Response response) {
        System.out.println("front");
    }
}
