package danil.xmltodb.service;

import danil.xmltodb.dao.BoxRepository;
import danil.xmltodb.model.db.Box;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxService {

    private final BoxRepository boxRepository;

    public BoxService(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    public void saveAll(List<Box> boxes) {
        boxRepository.saveAll(boxes);
    }

}
