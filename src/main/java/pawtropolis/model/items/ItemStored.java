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
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "blueprint_id")
    private ItemBlueprint itemBlueprint;
    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "inventory_id")
    private Inventory inventory;
    public ItemStored(ItemBlueprint itemBlueprint,int quantity){
        this.itemBlueprint = itemBlueprint;
        this.quantity = quantity;
    }
    public ItemStored() {}
    public void increaseQuantity(){
        this.quantity++;
    }

    public void decreaseQuantity(){
        this.quantity--;
    }
}
