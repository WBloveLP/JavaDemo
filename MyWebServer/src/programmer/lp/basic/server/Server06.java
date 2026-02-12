package programmer.lp.basic.server;

import programmer.lp.webserver.server.Request;
import programmer.lp.webserver.server.Response;
import programmer.lp.webserver.servlet.LoginServlet;
import programmer.lp.webserver.servlet.RegisterServlet;
import programmer.lp.webserver.servlet.Servlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
解耦业务代码到Servlet
 */
public class Server06 {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server06 server = new Server06();
        server.start();
    }

    //启动服务
    public void start() {
        try {
            serverSocket = new ServerSocket(8888);
            receive();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败....");
        }
    }

    //接受连接处理
    public void receive() {
        try {
            Socket client = serverSocket.accept();
            System.out.println("一个客户端建立了连接....");
            Request request = new Request(client);
            Response response = new Response(client);
            Servlet servlet = null;
            final String requestUri = request.getUri();
            if ("/login".equals(requestUri)) {
                servlet = new LoginServlet();
            } else if ("/register".equals(requestUri)) {
                servlet = new RegisterServlet();
            } else {
                // do something
            }

            servlet.service(request, response);
            response.pushToBrowser(200);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误");
        }
    }

    //停止服务
    public void stop() {
    }

}
