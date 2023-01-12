package pawtropolis.model.items;

public class Item {
    private String name;
    private String description;
    private int volume;

    public Item(){
        this.name=name;
        this.description=description;
        this.volume=volume;
    }

    public Item(String name, String description, int volume ) {
        this.name = name;
        this.description = description;
        this.volume = volume;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getVolume() {
        return volume;
    }
}
