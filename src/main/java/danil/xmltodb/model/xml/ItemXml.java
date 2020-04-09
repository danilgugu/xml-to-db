package danil.xmltodb.model.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Item")
public class ItemXml {

    @XmlAttribute
    private int id;

    @XmlAttribute
    private String color;

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }
}
