package pawtropolis.persistence.dao.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pawtropolis.model.map.Room;
import pawtropolis.persistence.dao.AbstractPersistanceClass;
import pawtropolis.persistence.dto.map.RoomDTO;
import pawtropolis.utility.marshall.ConcrateMarshaller;
@Component
public class RoomDAO extends AbstractPersistanceClass<RoomDTO, Room,Integer> {
    @Autowired
    protected RoomDAO(JpaRepository<RoomDTO, Integer> repository, ConcrateMarshaller<RoomDTO, Room> marshaller) {
        super(repository, marshaller);
    }

    @Override
    public Room save(RoomDTO dto) {
        return null;
    }

    @Override
    public Room edit(RoomDTO dto) {
        return null;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }

    @Override
    public Room get(Integer id) {
        return null;
    }
}
