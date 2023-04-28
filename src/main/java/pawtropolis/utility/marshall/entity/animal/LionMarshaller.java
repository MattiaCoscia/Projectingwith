package pawtropolis.utility.marshall.entity.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.species.LionDTO;
import pawtropolis.model.entity.npc.animal.species.Lion;
import pawtropolis.utility.marshall.ConcrateMarshaller;

@Component
public class LionMarshaller extends BaseAnimalWithTailMarshaller<LionDTO,Lion> implements ConcrateMarshaller<LionDTO,Lion> {
    @Autowired
    protected LionMarshaller() {
        super(Lion.class, LionDTO.class);
    }

    @Override
    public Lion marshall(LionDTO lionDTO) {
        if (!ObjectUtils.isEmpty(lionDTO)) {
            return super.marshallFromDTO(lionDTO, Lion.class);
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
