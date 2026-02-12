package programmer.lp.basic.servlet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {

//    HTTP协议是基于TCP协议的

    public static void main(String[] args) throws Exception {
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
                        .getResourceAsStream("programmer/lp/basic/servlet/web.xml")
                , handler);

        WebContext webContext = new WebContext(handler.getEntities(), handler.getMappings());
        final String urlPattern = "/register";
        final String servletClass = webContext.getServletClass(urlPattern);
        Servlet servlet =
                (Servlet) Class.forName(servletClass).getConstructor().newInstance();
        servlet.service();
    }

}