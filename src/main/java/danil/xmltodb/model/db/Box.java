package danil.xmltodb.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "box")
public class Box {

    @Id
    private int id;

    @Column(name = "contained_in")
    private int containedIn;

    public void setId(int id) {
        this.id = id;
    }

    public void setContainedIn(int containedIn) {
        this.containedIn = containedIn;
    }

}
