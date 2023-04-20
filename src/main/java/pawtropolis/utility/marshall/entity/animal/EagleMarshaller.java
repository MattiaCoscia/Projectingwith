package pawtropolis.utility.marshall.entity.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.species.EagleDTO;
import pawtropolis.model.entity.npc.animal.species.Eagle;
@Component
public class EagleMarshaller extends BaseAnimalWithWingsMarshaller<EagleDTO,Eagle> {
    @Autowired
    protected EagleMarshaller() {
        super(Eagle.class, EagleDTO.class);
    }

    @Override
    public Eagle marshall(EagleDTO EagleDTO) {
        if (!ObjectUtils.isEmpty(EagleDTO)) {
            return super.marshallFromDTO(EagleDTO, Eagle.class);
        }
        return null;
    }
    @Override
    public EagleDTO marshall(Eagle eagle) {
        if (!ObjectUtils.isEmpty(eagle)) {
            return super.marshallToDTO(eagle, EagleDTO.class);
        }
        return null;
    }
}
