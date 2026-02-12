package programmer.lp.basic.server;

import programmer.lp.webserver.server.Dispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
多线程处理，加入分发器
 */
public class Server08 {

    private ServerSocket serverSocket;
    private boolean isRunning;

    //启动服务
    public void start() {
        try {
            serverSocket = new ServerSocket(8888);
            isRunning = true;
            receive();
        } catch (IOException e) {
            e.printStackTrace();
            stop();
        }
    }

    //接受连接处理
    public void receive() {
        while (isRunning) {
            try {
                Socket client = serverSocket.accept();
                new Thread(new Dispatcher(client)).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //停止服务
    public void stop() {
        isRunning = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server08 server = new Server08();
        server.start();
    }

}

