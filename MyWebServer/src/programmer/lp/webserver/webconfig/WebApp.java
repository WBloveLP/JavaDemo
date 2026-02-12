package programmer.lp.webserver.webconfig;

import programmer.lp.webserver.servlet.Servlet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WebApp {

    private static WebContext webContext;

    static {
        try {
            //SAX解析
            //1、获取解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //2、从解析工厂获取解析器
            SAXParser parse = factory.newSAXParser();
            //3、编写处理器
            //4、加载文档 Document 注册处理器
            WebHandler handler = new WebHandler();
            //5、解析
            parse.parse(Thread.currentThread()
                            .getContextClassLoader()
                            .getResourceAsStream("web.xml")
                    , handler);
            webContext = new WebContext(handler.getEntities(), handler.getMappings());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Servlet getServletFromUri(String uri) {
//        final String uri = "/register";
        final String servletClass = webContext.getServletClass(uri);
        try {
            return (Servlet) Class.forName(servletClass).getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

}
