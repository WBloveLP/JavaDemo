package com.example.printreqpath.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/**")
public class PrintPathController {

    /*
        查看请求的Cookie和路径信息
     */

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String printPath(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        sb.append("Cookie: ");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                sb.append(cookie.getName()).append("=").append(cookie.getValue()).append("; ");
            }
        }
        return sb.append("\n")
                .append("路径：")
                .append(URLDecoder.decode(request.getRequestURI(), StandardCharsets.UTF_8)
                ).toString();
    }

}
