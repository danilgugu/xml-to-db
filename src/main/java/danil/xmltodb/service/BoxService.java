package danil.xmltodb.service;

import danil.xmltodb.dao.BoxDao;
import danil.xmltodb.model.db.Box;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxService {

    private final BoxDao boxDao;

    public BoxService(BoxDao boxDao) {
        this.boxDao = boxDao;
    }

    public void saveAll(List<Box> boxes) {
        boxDao.saveAll(boxes);
    }

}
