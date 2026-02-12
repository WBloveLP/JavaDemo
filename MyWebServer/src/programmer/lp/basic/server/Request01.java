package programmer.lp.basic.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
/*
封装请求信息 获取method uri params字符串
 */
public class Request01 {

    private static final String CRLF = "\r\n";

    private String requestInfo;
    private String method; // 小写
    private String uri; // 前面带/
    private String params = ""; // 浏览器地址栏可以写参数

    public Request01(Socket client) throws IOException {
        this(client.getInputStream());
    }

    public Request01(InputStream is) {
        try {
            byte[] buf = new byte[1024 * 1024];
            int len = is.read(buf);
            requestInfo = new String(buf, 0, len);
            parseRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseRequest() {
        method = requestInfo.substring(0, requestInfo.indexOf(" /")).toLowerCase();
        uri = requestInfo.substring(requestInfo.indexOf("/"), requestInfo.indexOf(" HTTP/"));
        int indexOfParam = uri.indexOf("?");
        if (indexOfParam >= 0) {
            params += uri.substring(indexOfParam + 1);
            uri = uri.substring(0, indexOfParam);
        }
        if ("post".equals(method)) {
            String param = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
            if (params.equals("")) {
                params += param;
            } else {
                params += "&";
                params += param;
            }
        }
        System.out.println(method);
        System.out.println(uri);
        System.out.println(params);
    }

}
