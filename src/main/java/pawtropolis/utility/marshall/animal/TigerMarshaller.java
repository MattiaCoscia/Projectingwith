package pawtropolis.utility.marshall.animal;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.dto.entity.npc.animal.species.TigerDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;
import pawtropolis.model.entity.npc.animal.species.Tiger;
@Component
public class TigerMarshaller extends BaseAnimalWithTailMarshaller {
    private Class<? extends Animal> businessClass = Tiger.class;
    private Class<? extends AnimalDTO> dtoClass = TigerDTO.class;

    public Tiger marshallFromDTO(TigerDTO tigerDTO) {
        if (!ObjectUtils.isEmpty(tigerDTO)) {
            return super.marshallFromDTO(tigerDTO, Tiger.class);
        }
        return null;
    }

    public TigerDTO marshallToDTO(Tiger tiger) {
        if (!ObjectUtils.isEmpty(tiger)) {
            return super.marshallToDTO(tiger, TigerDTO.class);
        }
        return null;
    }
}
