package pawtropolis.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.items.BagDTO;
import pawtropolis.model.items.Bag;
import pawtropolis.utility.marshall.ConcrateMarshaller;

import java.util.Optional;
@Component
public class BagDAO extends AbstractPersistanceClass<BagDTO, Bag,Integer>{
    @Autowired
    protected BagDAO(JpaRepository<BagDTO, Integer> repository, ConcrateMarshaller<BagDTO, Bag> marshaller) {
        super(repository, marshaller);
    }

    @Override
    public Bag save(BagDTO dto) {
        if(!ObjectUtils.isEmpty(dto)){
            getRepository().save(dto);
            return getMarshaller().marshall(dto);
        }
        return null;
    }

    @Override
    public Bag edit(BagDTO dto) {
        if(!ObjectUtils.isEmpty(dto)){
            Optional<BagDTO> optDTO = getRepository().findById(dto.getId());
            optDTO.ifPresent(bagDTO -> getRepository().save(dto));
            return getMarshaller().marshall(dto);
        }
        return null;
    }

    @Override
    public boolean remove(Integer id) {
        if(!ObjectUtils.isEmpty(id)){
            Optional<BagDTO> optDTO = getRepository().findById(id);
            optDTO.ifPresent(bagDTO -> getRepository().delete(bagDTO));
            return optDTO.isPresent();
        }
        return false;
    }

    @Override
    public Bag get(Integer id) {
        if(!ObjectUtils.isEmpty(id)){
            Optional<BagDTO> optDTO = getRepository().findById(id);
            if(optDTO.isPresent()){
                return getMarshaller().marshall(optDTO.get());
            }
        }
        return null;
    }
}
