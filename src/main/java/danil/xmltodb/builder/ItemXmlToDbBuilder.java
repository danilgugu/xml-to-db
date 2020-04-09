package danil.xmltodb.builder;

import danil.xmltodb.model.db.Item;
import danil.xmltodb.model.xml.ItemXml;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ItemXmlToDbBuilder {

    public Item build(ItemXml itemXml, Integer containedIn) {
        Item item = new Item();
        item.setId(itemXml.getId());
        String color = itemXml.getColor();
        if (Objects.nonNull(color) && !color.isEmpty()) {
            item.setColor(color);
        }
        if (Objects.nonNull(containedIn)) {
            item.setContainedIn(containedIn);
        }
        return item;
    }
}
