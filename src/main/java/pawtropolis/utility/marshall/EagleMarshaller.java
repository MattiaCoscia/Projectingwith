package pawtropolis.utility.marshall;

import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.species.EagleDTO;
import pawtropolis.model.entity.npc.animal.species.Eagle;

public class EagleMarshaller extends AnimalWithWingsMarshaller{
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
