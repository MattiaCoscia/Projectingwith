package pawtropolis.utility.marshall.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.dto.entity.npc.animal.species.LionDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;
import pawtropolis.model.entity.npc.animal.species.Lion;
@Component
public class LionMarshaller extends BaseAnimalWithTailMarshaller<LionDTO,Lion> {
    @Autowired
    protected LionMarshaller() {
        super(Lion.class, LionDTO.class);
    }

    @Override
    public Lion marshall(LionDTO LionDTO) {
        if (!ObjectUtils.isEmpty(LionDTO)) {
            return super.marshallFromDTO(LionDTO, Lion.class);
        }
        return null;
    }
    @Override
    public LionDTO marshall(Lion lion) {
        if (!ObjectUtils.isEmpty(lion)) {
            return super.marshallToDTO(lion, LionDTO.class);
        }
        return null;
    }
}
