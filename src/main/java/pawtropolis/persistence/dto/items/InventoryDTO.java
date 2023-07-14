package pawtropolis.persistence.dto.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.persistence.dto.DTOClass;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class InventoryDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="item_stored_inventory_correlation",
            joinColumns=
            @JoinColumn(name="inventory_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="item_stored_id", referencedColumnName="id")
    )
    @MapKeyColumn(name = "personalName",table = "item_stored_inventory_correlation")
    private Map<String, ItemStoredDTO> items;

    public InventoryDTO(){
        items=new HashMap<>();
    }
    public void addItemToInventory(ItemStoredDTO itemStored){
        if(itemStored != null){
            ItemStoredDTO itemStoredToAdd = items.get(itemStored.getPersonalName());
            if (itemStoredToAdd != null) {
                itemStoredToAdd.setQuantity(itemStoredToAdd.getQuantity() + 1);
            } else {
                this.getItems().put(itemStored.getPersonalName(), itemStored);
            }
        }
    }
}
