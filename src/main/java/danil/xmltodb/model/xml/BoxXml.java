package danil.xmltodb.model.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Box")
public class BoxXml {

    @XmlAttribute
    private int id;

    @XmlElement(name = "Box")
    private List<BoxXml> boxes;

    @XmlElement(name = "Item")
    private List<ItemXml> items;

    public int getId() {
        return id;
    }

    public List<BoxXml> getBoxes() {
        return boxes;
    }

    public List<ItemXml> getItems() {
        return items;
    }
}
