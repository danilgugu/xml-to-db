package danil.xmltodb.logic;

import danil.xmltodb.builder.BoxXmlToDbBuilder;
import danil.xmltodb.builder.ItemXmlToDbBuilder;
import danil.xmltodb.helper.FileHelper;
import danil.xmltodb.helper.XmlUnmarshaller;
import danil.xmltodb.model.db.Box;
import danil.xmltodb.model.db.Item;
import danil.xmltodb.model.xml.BoxXml;
import danil.xmltodb.model.xml.ItemXml;
import danil.xmltodb.model.xml.Storage;
import danil.xmltodb.service.BoxService;
import danil.xmltodb.service.ItemService;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class XmlToDbOperation {

    private final XmlUnmarshaller xmlUnmarshaller;
    private final BoxXmlToDbBuilder boxXmlToDbBuilder;
    private final ItemXmlToDbBuilder itemXmlToDbBuilder;
    private final BoxService boxService;
    private final ItemService itemService;
    private final FileHelper fileHelper;

    public XmlToDbOperation(XmlUnmarshaller xmlUnmarshaller,
                            BoxXmlToDbBuilder boxXmlToDbBuilder,
                            ItemXmlToDbBuilder itemXmlToDbBuilder,
                            BoxService boxService,
                            ItemService itemService, FileHelper fileHelper) {
        this.xmlUnmarshaller = xmlUnmarshaller;
        this.boxXmlToDbBuilder = boxXmlToDbBuilder;
        this.itemXmlToDbBuilder = itemXmlToDbBuilder;
        this.boxService = boxService;
        this.itemService = itemService;
        this.fileHelper = fileHelper;
    }

    public void process(String fileName) {
        try {
            Storage mainStorage = xmlUnmarshaller.unmarshall(fileName);
            saveAll(mainStorage);
        } catch (JAXBException e) {
            e.printStackTrace();
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
        logAllBoxes(boxesToSave);
        logAllItems(itemsToSave);
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

    private void logAllItems(List<Item> items) {
        String content = "Items: \n" +
                "ID CONTAINED_IN COLOR\n" + items.stream()
                .map(item -> {
                    String color = item.getColor();
                    if (Objects.isNull(color)) {
                        color = "";
                    }
                    return item.getId() + " " +
                            item.getContainedIn() + " " +
                            color;
                })
                .collect(Collectors.joining("\n"));
        fileHelper.write(content, "src/main/resources/items.log");
    }

    private void logAllBoxes(List<Box> boxes) {
        String content = "Boxes: \n" +
                "ID CONTAINED_IN\n" +boxes.stream()
                .map(box -> box.getId() + " " +
                        box.getContainedIn())
                .collect(Collectors.joining("\n"));
        fileHelper.write(content, "src/main/resources/boxes.log");
    }
}
