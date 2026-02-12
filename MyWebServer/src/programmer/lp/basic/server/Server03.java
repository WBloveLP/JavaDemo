package programmer.lp.basic.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
封装响应信息  只关注内容和状态码
 */
public class Server03 {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server03 server = new Server03();
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
            InputStream is = client.getInputStream();
            byte[] buf = new byte[1024 * 1024];
            int len = is.read(buf);
            String requestInfo = new String(buf, 0, len);
            System.out.println(requestInfo);

            // 输出响应
            Response response = new Response(client);
            response.println("<html>");
            response.println("<head>");
            response.println("<meta charset=\"UTF-8\">");
            response.println("<title>");
            response.println("服务器响应成功");
            response.println("</title>");
            response.println("</head>");
            response.println("<body>");
            response.println("server终于回来了。。。。");
            response.println("</body>");
            response.println("</html>");
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
