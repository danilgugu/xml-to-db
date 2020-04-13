package danil.xmltodb.logic;

import danil.xmltodb.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetItemsByColorAndContainedInOperation {

    private final ItemService itemService;

    public List<String> process(String color, Integer containedIn) {
        log.info("process started. Args: " + color + ", " + containedIn);
        List<String> result = itemService.findAllByColorAndContainedIn(color, containedIn).stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
        log.info("process finished: " + result);
        return result;
    }
}
