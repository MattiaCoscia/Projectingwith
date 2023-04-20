package pawtropolis.utility.marshall.item_related;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.items.InventoryDTO;
import pawtropolis.model.dto.items.ItemStoredDTO;
import pawtropolis.model.items.Inventory;
import pawtropolis.model.items.ItemStored;

@Component
public class InventoryMarshaller {
    private ItemStoredMarshaller itemStoredMarshaller;
    @Autowired
    public InventoryMarshaller(ItemStoredMarshaller itemStoredMarshaller){
        this.itemStoredMarshaller = itemStoredMarshaller;
    }
    public Inventory marhsallFromDTO(InventoryDTO inventoryDTO){
        if(!ObjectUtils.isEmpty(inventoryDTO)){
            Inventory inventory = new Inventory();
            inventory.setId(inventoryDTO.getId());
            for(ItemStoredDTO itemDTO:inventoryDTO.getItems().values()){
                ItemStored item = itemStoredMarshaller.marshallFromDTO(itemDTO);
                item.setInventory(inventory);
                inventory.addItemTOInventory(item);
            }
            return inventory;
        }
        return null;
    }
    public InventoryDTO marshallToDTO(Inventory inventory){
        if(!ObjectUtils.isEmpty(inventory)){
            InventoryDTO inventoryDTO = new InventoryDTO();
            inventoryDTO.setId(inventory.getId());
            for(ItemStored item:inventory.getItems().values()){
                ItemStoredDTO itemDTO = itemStoredMarshaller.marshallToDTO(item);
                itemDTO.setInventoryDTO(inventoryDTO);
                inventoryDTO.addItemToInventory(itemDTO);
            }
            return inventoryDTO;
        }
        return null;
    }
}
