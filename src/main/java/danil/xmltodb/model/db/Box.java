package danil.xmltodb.model.db;

public class Box {

    private int id;
    private int containedIn;

    public int getId() {
        return id;
    }

    public int getContainedIn() {
        return containedIn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContainedIn(int containedIn) {
        this.containedIn = containedIn;
    }

}
