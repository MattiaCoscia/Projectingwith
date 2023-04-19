package pawtropolis.utility.marshall.item_related;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.items.ItemStoredDTO;
import pawtropolis.model.items.ItemStored;
@Component
public class ItemStoredMarshaller {
    private ItemBlueprintMarhsaller blueprintMarhsaller;
    @Autowired
    public ItemStoredMarshaller(ItemBlueprintMarhsaller itemBlueprintMarhsaller){
        this.blueprintMarhsaller = itemBlueprintMarhsaller;
    }
    public ItemStored marshallFromDTO(ItemStoredDTO itemStoredDTO){
        if(!ObjectUtils.isEmpty(itemStoredDTO)
                && !ObjectUtils.isEmpty(itemStoredDTO.getItemBlueprintDTO())){
            ItemStored itemStored = new ItemStored();
            itemStored.setId(itemStoredDTO.getId());
            itemStored.setItemBlueprint(blueprintMarhsaller.marhsallFromDTO(itemStoredDTO.getItemBlueprintDTO()));
            itemStored.setQuantity(itemStoredDTO.getQuantity());
            return itemStored;
        }
        return null;
    }
    public ItemStoredDTO marshallToDTO(ItemStored itemStored){
        if(!ObjectUtils.isEmpty(itemStored)
                && !ObjectUtils.isEmpty(itemStored.getItemBlueprint())){
            ItemStoredDTO itemStoredDTO = new ItemStoredDTO();
            itemStoredDTO.setId(itemStored.getId());
            itemStoredDTO.setQuantity(itemStored.getQuantity());
            itemStoredDTO.setItemBlueprintDTO(blueprintMarhsaller.marshallToDTO(itemStored.getItemBlueprint()));
            return itemStoredDTO;
        }
        return null;
    }
}
