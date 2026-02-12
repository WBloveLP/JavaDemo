package programmer.lp.basic.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebContext {

    private Map<String, String> entityMap = new HashMap<>();
    private Map<String, String> mappingMap = new HashMap<>();

    public WebContext(List<Entity> entities, List<Mapping> mappings) {
        for (Entity entity : entities) {
            entityMap.put(entity.getName(), entity.getClz());
        }
        for (Mapping mapping : mappings) {
            final Set<String> urlPatterns = mapping.getUrlPatterns();
            for (String urlPattern : urlPatterns) {
                mappingMap.put(urlPattern, mapping.getName());
            }
        }
    }

    public String getServletClass(String urlPattern) {
        return entityMap.get(mappingMap.get(urlPattern));
    }

}
