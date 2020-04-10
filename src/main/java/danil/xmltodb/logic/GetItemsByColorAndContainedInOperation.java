package danil.xmltodb.logic;

import danil.xmltodb.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetItemsByColorAndContainedInOperation {

    private final ItemService itemService;

    public GetItemsByColorAndContainedInOperation(ItemService itemService) {
        this.itemService = itemService;
    }

    public List<String> process(String color, Integer containedIn) {
        return itemService.findAllByColorAndContainedIn(color, containedIn).stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
