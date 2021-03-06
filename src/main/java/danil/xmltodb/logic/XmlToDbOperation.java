package danil.xmltodb.logic;

import danil.xmltodb.builder.BoxXmlToDbBuilder;
import danil.xmltodb.builder.ItemXmlToDbBuilder;
import danil.xmltodb.helper.EntityFileLogger;
import danil.xmltodb.helper.XmlUnmarshaller;
import danil.xmltodb.model.db.Box;
import danil.xmltodb.model.db.Item;
import danil.xmltodb.model.xml.BoxXml;
import danil.xmltodb.model.xml.ItemXml;
import danil.xmltodb.model.xml.Storage;
import danil.xmltodb.service.BoxService;
import danil.xmltodb.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class XmlToDbOperation {

    private final XmlUnmarshaller xmlUnmarshaller;
    private final BoxXmlToDbBuilder boxXmlToDbBuilder;
    private final ItemXmlToDbBuilder itemXmlToDbBuilder;
    private final BoxService boxService;
    private final ItemService itemService;
    private final EntityFileLogger entityFileLogger;

    public void process(String fileName) {
        log.info("XmlToDbOperation.process started. File to parse: " + fileName);
        try {
            Storage mainStorage = xmlUnmarshaller.unmarshall(fileName);
            saveAll(mainStorage);
            log.info("XmlToDbOperation.process finished.");
        } catch (JAXBException e) {
            log.error("JAXBException", e.getCause());
        }
    }

    private void saveAll(Storage storage) {
        List<ItemXml> items = storage.getItems();
        List<Item> itemsToSave = new ArrayList<>();
        if (Objects.nonNull(items) && !items.isEmpty()) {
            itemsToSave.addAll(items.stream()
                    .map(itemXml -> itemXmlToDbBuilder.build(itemXml, null))
                    .collect(Collectors.toList()));
        }
        List<BoxXml> boxes = storage.getBoxes();
        List<Box> boxesToSave = new ArrayList<>();
        getAllBoxesAndItemsContainedIn(boxes, boxesToSave, itemsToSave, null);
        boxService.saveAll(boxesToSave);
        itemService.saveAll(itemsToSave);
        entityFileLogger.logAll(boxesToSave, itemsToSave);
    }

    private void getAllBoxesAndItemsContainedIn(List<BoxXml> boxes,
                                                List<Box> boxesToSave,
                                                List<Item> itemsToSave,
                                                Integer containsIn) {
        if (Objects.nonNull(boxes) && !boxes.isEmpty()) {
            boxesToSave.addAll(boxes.stream()
                .map(boxXml -> boxXmlToDbBuilder.build(boxXml, containsIn))
                .collect(Collectors.toList()));
            boxes.forEach(boxXml -> {
                int currentBoxId = boxXml.getId();
                List<ItemXml> innerItems = boxXml.getItems();
                if (Objects.nonNull(innerItems) && !innerItems.isEmpty()) {
                    itemsToSave.addAll(innerItems.stream()
                            .map(innerItem -> itemXmlToDbBuilder.build(innerItem, currentBoxId))
                            .collect(Collectors.toList()));
                }
                List<BoxXml> innerBoxes = boxXml.getBoxes();
                getAllBoxesAndItemsContainedIn(innerBoxes, boxesToSave, itemsToSave, currentBoxId);
            });
        }
    }

}
