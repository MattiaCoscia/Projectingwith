package pawtropolis.utility.marshall;

import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.species.TigerDTO;
import pawtropolis.model.entity.npc.animal.species.Tiger;

public class TigerMarshaller extends AnimalWithTailMarshaller{
    public Tiger marshallFromDTO(TigerDTO tigerDTO){
        if(!ObjectUtils.isEmpty(tigerDTO)){
            return super.marshallFromDTO(tigerDTO,Tiger.class);
        }
        return null;
    }

    public TigerDTO marshallToDTO(Tiger tiger){
        if(!ObjectUtils.isEmpty(tiger)){
            return super.marshallToDTO(tiger,TigerDTO.class);
        }
        return null;
    }
}
