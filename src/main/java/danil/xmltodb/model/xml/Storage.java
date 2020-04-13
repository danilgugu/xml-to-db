package danil.xmltodb.model.xml;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@XmlRootElement(name = "Storage")
public class Storage {

    @XmlElement(name = "Box")
    private List<BoxXml> boxes;

    @XmlElement(name = "Item")
    private List<ItemXml> items;

}
