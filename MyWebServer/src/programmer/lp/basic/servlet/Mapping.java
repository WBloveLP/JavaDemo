package programmer.lp.basic.servlet;

/*
    <servlet-mapping>
        <servlet-name>login</servlet-name>
        <url-pattern>/login</url-pattern>
        <url-pattern>/g</url-pattern>
    </servlet-mapping>
 */

import java.util.HashSet;
import java.util.Set;

public class Mapping {

    private String name;
    private Set<String> urlPatterns;

    public Mapping() {
        urlPatterns = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(Set<String> urlPatterns) {
        this.urlPatterns = urlPatterns;
    }

    public void addUrlPattern(String urlPattern) {
        urlPatterns.add(urlPattern);
    }

}
