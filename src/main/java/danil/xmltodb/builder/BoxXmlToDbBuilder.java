package danil.xmltodb.builder;

import danil.xmltodb.model.db.Box;
import danil.xmltodb.model.xml.BoxXml;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BoxXmlToDbBuilder {

    public Box build(BoxXml boxXml, Integer containedIn) {
        Box box = new Box();
        box.setId(boxXml.getId());
        if (Objects.nonNull(containedIn)) {
            box.setContainedIn(containedIn);
        }
        return box;
    }
}
