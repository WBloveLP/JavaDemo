package programmer.lp.webserver.webconfig;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebContext {

    private final Map<String, String> entityMap = new HashMap<>();
    private final Map<String, String> mappingMap = new HashMap<>();
    private final Map<String, String> mappingALlMap = new HashMap<>();

    public WebContext(List<Entity> entities, List<Mapping> mappings) {
        for (Entity entity : entities) {
            entityMap.put(entity.getName(), entity.getClz());
        }
        for (Mapping mapping : mappings) {
            final Set<String> urlPatterns = mapping.getUrlPatterns();
            for (String urlPattern : urlPatterns) {
                if (urlPattern.endsWith("*")) {
                    urlPattern = urlPattern.substring(0, urlPattern.lastIndexOf("/*"));
                    mappingALlMap.put(urlPattern, mapping.getName());
                } else {
                    mappingMap.put(urlPattern, mapping.getName());
                }
            }
        }
    }

    public String getServletClass(String urlPattern) {
        final Set<Map.Entry<String, String>> entries = mappingALlMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            final String key = entry.getKey();
            if (urlPattern.startsWith(key)) {
                return entityMap.get(mappingALlMap.get(key));
            }
        }
        return entityMap.get(mappingMap.get(urlPattern));
    }

}
