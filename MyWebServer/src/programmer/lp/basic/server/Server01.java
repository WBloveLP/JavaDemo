package programmer.lp.basic.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server01 {

    private ServerSocket serverSocket;

    public void start() {
        try {
            serverSocket = new ServerSocket(8888);
            receive();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }

    public void stop() {
    }

    public void receive() {
        try {
            final Socket client = serverSocket.accept();
            // InputStream is = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            final char[] buf = new char[1024];
            int len;
            while ((len = reader.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端连接失败");
        }
    }

    public static void main(String[] args) {
        new Server01().start();
    }

}
