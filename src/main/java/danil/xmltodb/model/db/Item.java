package danil.xmltodb.model.db;

public class Item {

    private int id;
    private String color;
    private int containedIn;

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public int getContainedIn() {
        return containedIn;
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
