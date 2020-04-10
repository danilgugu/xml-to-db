package danil.xmltodb.service;

import danil.xmltodb.dao.ItemDao;
import danil.xmltodb.model.db.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemDao itemDao;

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void saveAll(List<Item> items) {
        itemDao.saveAll(items);
    }

    public List<Integer> findAllByColorAndContainedIn(String color, Integer containedIn) {
        return itemDao.findAllByColorAndContainedIn(color, containedIn);
    }
}
