package programmer.lp.basic.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
封装请求信息 处理请求参数和中文问题
 */

public class Server05 {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server05 server = new Server05();
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
            // 获取请求协议
            Request02 request = new Request02(client);

            // 输出响应
            Response response = new Response(client);
            response.println("<html>")
                    .println("<head>")
                    .println("<meta charset=\"UTF-8\">")
                    .println("<title>")
                    .println("服务器响应成功")
                    .println("</title>")
                    .println("</head>")
                    .println("<body>")
//                    .println(Arrays.toString(request.getParameters("username")))
//                    .println(request.getParameter("username"))
                    .println("server终于回来了。。。。")
                    .println("</body>")
                    .println("</html>")
                    .pushToBrowser(200);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误");
        }
    }

    //停止服务
    public void stop() {
    }

}
