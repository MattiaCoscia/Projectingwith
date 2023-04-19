package pawtropolis.utility.marshall.item_related;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.items.ItemBlueprintDTO;
import pawtropolis.model.items.ItemBlueprint;
@Component
public class ItemBlueprintMarhsaller {
    public ItemBlueprint marhsallFromDTO(ItemBlueprintDTO itemBlueprintDTO){
        if(!ObjectUtils.isEmpty(itemBlueprintDTO)){
            ItemBlueprint itemBlueprint = new ItemBlueprint();
            itemBlueprint.setDescription(itemBlueprint.getDescription());
            itemBlueprint.setId(itemBlueprintDTO.getId());
            itemBlueprint.setName(itemBlueprintDTO.getName());
            itemBlueprint.setVolume(itemBlueprintDTO.getVolume());
            return itemBlueprint;
        }
        return null;
    }

    public ItemBlueprintDTO marshallToDTO(ItemBlueprint itemBlueprint){
        if(!ObjectUtils.isEmpty(itemBlueprint)){
            ItemBlueprintDTO itemBlueprintDTO = new ItemBlueprintDTO();
            itemBlueprintDTO.setDescription(itemBlueprint.getDescription());
            itemBlueprintDTO.setVolume(itemBlueprint.getVolume());
            itemBlueprintDTO.setName(itemBlueprint.getName());
            itemBlueprintDTO.setId(itemBlueprint.getId());
            return itemBlueprintDTO;
        }
        return null;
    }
}
