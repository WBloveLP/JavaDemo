package programmer.lp.basic.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server02 {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server02 server = new Server02();
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
            StringBuilder content = new StringBuilder();
            content.append("<html>");
            content.append("<head>");
            content.append("<meta charset=\"UTF-8\">");
            content.append("<title>");
            content.append("服务器响应成功");
            content.append("</title>");
            content.append("</head>");
            content.append("<body>");
            content.append("server终于回来了。。。。");
            content.append("</body>");
            content.append("</html>");
            int contentSize = content.toString().getBytes().length; //必须获取字节长度
            StringBuilder responseInfo = new StringBuilder();
            String blank = " ";
            String CRLF = "\r\n";
            //返回
            //1、响应行: HTTP/1.1 200 OK
            responseInfo.append("HTTP/1.1").append(blank);
            responseInfo.append(200).append(blank);
            responseInfo.append("OK").append(CRLF);
            //2、响应头(最后一行存在空行):
			/*
            Date:Mon,31Dec209904:25:57GMT
            Content-type:text/html
            Content-length:39725426
			 */
            responseInfo.append("Date: ").append(new Date()).append(CRLF);
            responseInfo.append("Content-Type: text/html").append(CRLF);
            responseInfo.append("Content-Length: ").append(contentSize).append(CRLF);
            // 空行
            responseInfo.append(CRLF);
            //3、响应正文
            responseInfo.append(content.toString());

            //写出到客户端
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            writer.write(responseInfo.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误");
        }
    }

    //停止服务
    public void stop() {
    }

}
