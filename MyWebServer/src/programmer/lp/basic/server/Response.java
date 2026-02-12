package programmer.lp.basic.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Response {

    private static final String CRLF = "\r\n";
    private static final String BLANK = " ";

    private BufferedWriter writer;
    private final StringBuilder responseContent = new StringBuilder();
    private final StringBuilder responseHeaders = new StringBuilder();

    public Response(Socket client) throws IOException {
        this(client.getOutputStream());
    }

    public Response(OutputStream outputStream) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Response print(String content) {
        responseContent.append(content);
        return this;
    }

    public Response println(String content) {
        responseContent.append(content).append(CRLF);
        return this;
    }

    public void createHeader(int code) {
        //1、响应行: HTTP/1.1 200 OK
        responseHeaders.append("HTTP/1.1").append(BLANK);
        responseHeaders.append(code).append(BLANK);
        String status = "OK";
        switch (code) {
            case 404:
                status = "NOT FOUND";
                break;
            case 500:
                status = "SERVER ERROR";
                break;
        }
        responseHeaders.append(status).append(CRLF);
        //2、响应头(最后一行存在空行):
        /*
        Date: Mon,31Dec209904:25:57GMT
        Content-Type: text/html
        Content-Length: 39725426
         */
        responseHeaders.append("Date: ").append(new Date()).append(CRLF);
        responseHeaders.append("Content-Type: text/html").append(CRLF);
        responseHeaders.append("Content-Length: ")
                .append(responseContent.toString().getBytes(StandardCharsets.UTF_8).length)
                .append(CRLF);
        //3、空行
        responseHeaders.append(CRLF);
    }

    public void pushToBrowser(int code) {
        // 响应正文
        try {
            createHeader(code);
            writer.append(responseHeaders.toString());
            writer.append(responseContent.toString());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
