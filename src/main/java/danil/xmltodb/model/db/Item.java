package danil.xmltodb.model.db;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    private int id;
    private String color;
    private int containedIn;

}
