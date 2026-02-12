package programmer.lp.webserver.webconfig;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class WebHandler extends DefaultHandler {

    private final List<Entity> entities = new ArrayList<>();
    private final List<Mapping> mappings = new ArrayList<>();
    private Entity entity;
    private Mapping mapping;
    private String currentTag;
    private boolean isMapping;

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("servlet")) {
            entity = new Entity();
            isMapping = false;
        } else if (qName.equals("servlet-mapping")) {
            mapping = new Mapping();
            isMapping = true;
        }
        currentTag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("servlet")) {
            entities.add(entity);
        } else if (qName.equals("servlet-mapping")) {
            mappings.add(mapping);
        }
        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length).trim();
        if (currentTag != null) {
            if (isMapping) {
                if ("servlet-name".equals(currentTag)) {
                    mapping.setName(content);
                } else if ("url-pattern".equals(currentTag)) {
                    mapping.addUrlPattern(content);
                }
            } else {
                if ("servlet-name".equals(currentTag)) {
                    entity.setName(content);
                } else if ("servlet-class".equals(currentTag)) {
                    entity.setClz(content);
                }
            }
        }
    }

}