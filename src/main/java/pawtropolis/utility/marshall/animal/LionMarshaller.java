package pawtropolis.utility.marshall.animal;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.dto.entity.npc.animal.species.LionDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;
import pawtropolis.model.entity.npc.animal.species.Lion;
@Component
public class LionMarshaller extends BaseAnimalWithTailMarshaller {
    private Class<? extends Animal> businessClass = Lion.class;
    private Class<? extends AnimalDTO> dtoClass = LionDTO.class;
    public Lion marshallFromDTO(LionDTO LionDTO) {
        if (!ObjectUtils.isEmpty(LionDTO)) {
            return super.marshallFromDTO(LionDTO, Lion.class);
        }
        return null;
    }

    public LionDTO marshallToDTO(Lion lion) {
        if (!ObjectUtils.isEmpty(lion)) {
            return super.marshallToDTO(lion, LionDTO.class);
        }
        return null;
    }
}
