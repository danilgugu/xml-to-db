package danil.xmltodb.service;

import danil.xmltodb.dao.BoxDao;
import danil.xmltodb.model.db.Box;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoxService {

    private final BoxDao boxDao;

    public void saveAll(List<Box> boxes) {
        boxDao.saveAll(boxes);
    }

}
