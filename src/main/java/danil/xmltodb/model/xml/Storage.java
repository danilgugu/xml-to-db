package danil.xmltodb.model.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Storage")
public class Storage {

    @XmlElement(name = "Box")
    private List<BoxXml> boxes;

    @XmlElement(name = "Item")
    private List<ItemXml> items;

    public List<BoxXml> getBoxes() {
        return boxes;
    }

    public List<ItemXml> getItems() {
        return items;
    }

}
