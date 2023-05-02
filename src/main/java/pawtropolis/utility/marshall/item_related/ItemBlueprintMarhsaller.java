package pawtropolis.utility.marshall.item_related;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.items.ItemBlueprintDTO;
import pawtropolis.model.items.ItemBlueprint;
import pawtropolis.utility.marshall.ConcrateMarshaller;

@Component
public class ItemBlueprintMarhsaller implements ConcrateMarshaller<ItemBlueprintDTO,ItemBlueprint> {
    @Override
    public ItemBlueprint marshall(ItemBlueprintDTO itemBlueprintDTO) {
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
    @Override
    public ItemBlueprintDTO marshall(ItemBlueprint itemBlueprint){
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
