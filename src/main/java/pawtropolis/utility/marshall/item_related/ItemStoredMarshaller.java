package pawtropolis.utility.marshall.item_related;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.items.ItemStoredDTO;
import pawtropolis.model.items.ItemStored;
import pawtropolis.utility.marshall.ConcrateMarshaller;

@Component
public class ItemStoredMarshaller implements ConcrateMarshaller<ItemStoredDTO,ItemStored> {
    private ItemBlueprintMarhsaller blueprintMarhsaller;
    @Autowired
    public ItemStoredMarshaller(ItemBlueprintMarhsaller itemBlueprintMarhsaller){
        this.blueprintMarhsaller = itemBlueprintMarhsaller;
    }
    @Override
    public ItemStored marshall(ItemStoredDTO itemStoredDTO){
        if(!ObjectUtils.isEmpty(itemStoredDTO)
                && !ObjectUtils.isEmpty(itemStoredDTO.getItemBlueprintDTO())){
            ItemStored itemStored = new ItemStored();
            itemStored.setId(itemStoredDTO.getId());
            itemStored.setPersonalName(itemStoredDTO.getPersonalName());
            itemStored.setItemBlueprint(blueprintMarhsaller.marshall(itemStoredDTO.getItemBlueprintDTO()));
            itemStored.setQuantity(itemStoredDTO.getQuantity());
            return itemStored;
        }
        return null;
    }
    @Override
    public ItemStoredDTO marshall(ItemStored itemStored){
        if(!ObjectUtils.isEmpty(itemStored)
                && !ObjectUtils.isEmpty(itemStored.getItemBlueprint())){
            ItemStoredDTO itemStoredDTO = new ItemStoredDTO();
            itemStoredDTO.setId(itemStored.getId());
            itemStoredDTO.setPersonalName(itemStored.getPersonalName());
            itemStoredDTO.setQuantity(itemStored.getQuantity());
            itemStoredDTO.setItemBlueprintDTO(blueprintMarhsaller.marshall(itemStored.getItemBlueprint()));
            return itemStoredDTO;
        }
        return null;
    }
}
