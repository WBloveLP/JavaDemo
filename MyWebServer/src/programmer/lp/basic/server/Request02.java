package programmer.lp.basic.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

/*
封装请求信息 处理请求参数为Map和中文问题
 */
public class Request02 {

    private static final String CRLF = "\r\n";

    private String requestInfo;
    private String method; // 小写
    private String uri; // 前面带/
    private String params = ""; // 浏览器地址栏可以写参数
    private Map<String, List<String>> paramsMap;

    public Request02(Socket client) throws IOException {
        this(client.getInputStream());
    }

    public Request02(InputStream is) {
        paramsMap = new HashMap<>();
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
//        System.out.println(method);
//        System.out.println(uri);
//        System.out.println(params);

        parseParams();

    }

    private void parseParams() {
        if (params.length() <= 0) return;
        final String[] keyValues = params.split("&");
        for (String keyValue : keyValues) {
            String[] kvArr = keyValue.split("=");
            kvArr = Arrays.copyOf(kvArr, 2);
            String key = kvArr[0];
            String value = kvArr[1];
            if (!paramsMap.containsKey(key)) {
                paramsMap.put(key, new ArrayList<>());
            }
            value = value == null ? "" : value;
            if (method.equals("get")) {
                value = decodeGetParam(value, "UTF-8");
            }
            paramsMap.get(key).add(value);
        }
    }

    private String decodeGetParam(String value, String code) {
        try {
            return java.net.URLDecoder.decode(value, code);
        } catch (UnsupportedEncodingException e) {
            return value;
        }
    }

    public String[] getParameters(String name) {
        if (!paramsMap.containsKey(name)) return null;
        return paramsMap.get(name).toArray(new String[]{});
    }

    public String getParameter(String name) {
        try {
            return getParameters(name)[0];
        } catch (Exception e) {
            return "";
        }
    }

}
