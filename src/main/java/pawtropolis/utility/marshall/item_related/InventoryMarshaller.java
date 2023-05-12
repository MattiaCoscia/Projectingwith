package pawtropolis.utility.marshall.item_related;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.persistence.dto.items.InventoryDTO;
import pawtropolis.persistence.dto.items.ItemStoredDTO;
import pawtropolis.model.items.Inventory;
import pawtropolis.model.items.ItemStored;
import pawtropolis.utility.marshall.ConcrateMarshaller;

@Component
public class InventoryMarshaller implements ConcrateMarshaller<InventoryDTO,Inventory> {
    private ItemStoredMarshaller itemStoredMarshaller;
    @Autowired
    public InventoryMarshaller(ItemStoredMarshaller itemStoredMarshaller){
        this.itemStoredMarshaller = itemStoredMarshaller;
    }
    @Override
    public Inventory marshall(InventoryDTO inventoryDTO) {
        if(!ObjectUtils.isEmpty(inventoryDTO)){
            Inventory inventory = new Inventory();
            inventory.setId(inventoryDTO.getId());
            for(ItemStoredDTO itemDTO:inventoryDTO.getItems().values()){
                ItemStored item = itemStoredMarshaller.marshall(itemDTO);
                item.setInventory(inventory);
                inventory.addItemTOInventory(item);
            }
            return inventory;
        }
        return null;
    }

    @Override
    public InventoryDTO marshall(Inventory inventory){
        if(!ObjectUtils.isEmpty(inventory)){
            InventoryDTO inventoryDTO = new InventoryDTO();
            inventoryDTO.setId(inventory.getId());
            for(ItemStored item:inventory.getItems().values()){
                ItemStoredDTO itemDTO = itemStoredMarshaller.marshall(item);
                itemDTO.setInventoryDTO(inventoryDTO);
                inventoryDTO.addItemToInventory(itemDTO);
            }
            return inventoryDTO;
        }
        return null;
    }
}
