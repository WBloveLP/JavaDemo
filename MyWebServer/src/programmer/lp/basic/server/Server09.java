package programmer.lp.basic.server;

/*
处理404 500页面
 */
public class Server09 {
// 主要是Dispatcher

    /*

    package programmer.lp.webserver.server;

import programmer.lp.webserver.servlet.Servlet;
import programmer.lp.webserver.webconfig.WebApp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Dispatcher implements Runnable {

    private Socket client;
    private Request request;
    private Response response;

    public Dispatcher(Socket client) {
        this.client = client;
        try {
            request = new Request(client);
            response = new Response(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            final String requestUri = request.getUri();
            if (requestUri.equals("") || requestUri.equals("/")) {
                goIndex();
                release();
                return;
            }
            final Servlet servlet = WebApp.getServletFromUri(requestUri);
            if (null != servlet) {
                servlet.service(request, response);
                response.pushToBrowser(200);
            } else {
                goError(404);
            }
        } catch (Exception e) {
            e.printStackTrace();
            goError(500);
        }
        release();
    }

    private void goError(int code) {
        // 读取error.html页面
        String info = readFromResource("error.html");
        info = info.replace("#error#", code + "");
        response.print(info);
        response.pushToBrowser(code);
    }

    private void goIndex() {
        // 读取index.html页面
        String info = readFromResource("index.html");
        response.print(info);
        response.pushToBrowser(200);
    }

    private String readFromResource(String fileName) {
        StringBuilder info = new StringBuilder();
        try (final InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);) {
            int len;
            byte[] buf = new byte[1024];
            while ((len = resourceAsStream.read(buf)) != -1) {
                info.append(new String(buf, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info.toString();
    }

    private void release() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

    * */

}

