package pawtropolis.utility.marshall.animal;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.dto.entity.npc.animal.species.EagleDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;
import pawtropolis.model.entity.npc.animal.species.Eagle;
@Component
public class EagleMarshaller extends BaseAnimalWithWingsMarshaller {
    private Class<? extends Animal> businessClass = Eagle.class;
    private Class<? extends AnimalDTO> dtoClass = EagleDTO.class;
    public Eagle marshallFromDTO(EagleDTO EagleDTO) {
        if (!ObjectUtils.isEmpty(EagleDTO)) {
            return super.marshallFromDTO(EagleDTO, Eagle.class);
        }
        return null;
    }

    public EagleDTO marshallToDTO(Eagle eagle) {
        if (!ObjectUtils.isEmpty(eagle)) {
            return super.marshallToDTO(eagle, EagleDTO.class);
        }
        return null;
    }
}
