package danil.xmltodb.model.xml;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "Item")
public class ItemXml {

    @XmlAttribute
    private int id;

    @XmlAttribute
    private String color;

}
