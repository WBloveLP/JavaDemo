package programmer.lp.basic.server;

import programmer.lp.webserver.server.Request;
import programmer.lp.webserver.servlet.Servlet;
import programmer.lp.webserver.webconfig.WebApp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
整合配置文件和反射
 */
public class Server07 {

    private ServerSocket serverSocket;

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
            Request request = new Request(client);
            programmer.lp.webserver.server.Response response = new programmer.lp.webserver.server.Response(client);
            final String requestUri = request.getUri();
            final Servlet servlet = WebApp.getServletFromUri(requestUri);
            if (null != servlet) {
                servlet.service(request, response);
                response.pushToBrowser(200);
            } else {
                response.pushToBrowser(404);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //停止服务
    public void stop() {
    }

    public static void main(String[] args) {
        Server07 server = new Server07();
        server.start();
    }

}