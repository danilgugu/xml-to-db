package danil.xmltodb.service;

import danil.xmltodb.dao.ItemRepository;
import danil.xmltodb.model.db.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveAll(List<Item> items) {
        itemRepository.saveAll(items);
    }
}
