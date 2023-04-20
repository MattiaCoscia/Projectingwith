package pawtropolis.utility.marshall.animal;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.dto.entity.npc.animal.species.TigerDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;
import pawtropolis.model.entity.npc.animal.species.Tiger;
@Component
public class TigerMarshaller extends BaseAnimalWithTailMarshaller<TigerDTO,Tiger> {
    private final Class<? extends Animal> businessClass = Tiger.class;
    private final Class<? extends AnimalDTO> dtoClass = TigerDTO.class;
    @Override
    public Tiger marshall(TigerDTO tigerDTO) {
        if (!ObjectUtils.isEmpty(tigerDTO)) {
            return super.marshallFromDTO(tigerDTO, Tiger.class);
        }
        return null;
    }
    @Override
    public TigerDTO marshall(Tiger tiger) {
        if (!ObjectUtils.isEmpty(tiger)) {
            return super.marshallToDTO(tiger, TigerDTO.class);
        }
        return null;
    }
}
