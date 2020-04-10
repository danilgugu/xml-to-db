package danil.xmltodb.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

    @Id
    private int id;

    private String color;

    @Column(name = "contained_in")
    private int containedIn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setContainedIn(int containedIn) {
        this.containedIn = containedIn;
    }

}
