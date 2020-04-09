package danil.xmltodb.helper;

import danil.xmltodb.model.xml.BoxXml;
import danil.xmltodb.model.xml.ItemXml;
import danil.xmltodb.model.xml.Storage;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class XmlUnmarshaller {

    private final Unmarshaller unmarshaller;
    private final FileHelper fileHelper;

    public XmlUnmarshaller(FileHelper fileHelper) throws JAXBException {
        this.fileHelper = fileHelper;
        this.unmarshaller = JAXBContext.newInstance(Storage.class, BoxXml.class, ItemXml.class).createUnmarshaller();
    }

    public Storage unmarshall(String filePath) throws JAXBException {
        File xml = fileHelper.getFile(filePath);
        return (Storage) unmarshaller.unmarshal(xml);
    }

}
