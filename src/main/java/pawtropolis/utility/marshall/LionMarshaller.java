package pawtropolis.utility.marshall;

import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.species.LionDTO;
import pawtropolis.model.entity.npc.animal.species.Lion;

public class LionMarshaller extends AnimalWithTailMarshaller {
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
