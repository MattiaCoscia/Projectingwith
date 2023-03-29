package pawtropolis.model.items;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class ItemStored {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Transient
    private String name;
    @Transient
    private String description;
    @Transient
    private int volume;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "blueprint_id")
    private ItemBlueprint itemBlueprint;
    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "inventory_id")
    private Inventory inventory;
    public ItemStored(String name,String description,int volume,int quantity){
        this.name = name;
        this.description = description;
        this.volume = volume;
        this.quantity = quantity;
    }
    public ItemStored() {}
    public void increaseQuantity(){
        this.quantity++;
    }

    public void decreaseQuantity(){
        this.quantity--;
    }

    public void setValues(){
        this.name = itemBlueprint.getName();
        this.description = itemBlueprint.getDescription();
        this.volume = itemBlueprint.getVolume();
    }
}
