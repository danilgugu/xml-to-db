package danil.xmltodb.helper;

import danil.xmltodb.model.db.Box;
import danil.xmltodb.model.db.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntityFileLogger {

    private final FileHelper fileHelper;

    public void logAll(List<Box> boxes, List<Item> items) {
        logAllBoxes(boxes);
        logAllItems(items);
    }

    public void logAllBoxes(List<Box> boxes) {
        String content = "Boxes: \n" +
                "ID CONTAINED_IN\n" +boxes.stream()
                .map(box -> box.getId() + " " +
                        box.getContainedIn())
                .collect(Collectors.joining("\n"));
        fileHelper.write(content, "src/main/resources/boxes.log");
    }

    public void logAllItems(List<Item> items) {
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
}
