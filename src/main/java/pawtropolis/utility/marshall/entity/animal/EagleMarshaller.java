package pawtropolis.utility.marshall.entity.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.persistence.dto.entity.npc.animal.species.EagleDTO;
import pawtropolis.model.entity.npc.animal.species.Eagle;
import pawtropolis.utility.marshall.ConcrateMarshaller;

@Component
public class EagleMarshaller extends BaseAnimalWithWingsMarshaller<EagleDTO,Eagle> implements ConcrateMarshaller<EagleDTO,Eagle> {
    @Autowired
    protected EagleMarshaller() {
        super(Eagle.class, EagleDTO.class);
    }

    @Override
    public Eagle marshall(EagleDTO eagleDTO) {
        if (!ObjectUtils.isEmpty(eagleDTO)) {
            return super.marshallFromDTO(eagleDTO, Eagle.class);
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
