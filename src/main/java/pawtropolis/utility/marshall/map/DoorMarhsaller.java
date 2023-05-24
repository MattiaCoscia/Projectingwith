package pawtropolis.utility.marshall.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.map.Door;
import pawtropolis.persistence.dto.map.DoorDTO;
import pawtropolis.utility.marshall.ConcrateMarshaller;
import pawtropolis.utility.marshall.item_related.ItemStoredMarshaller;

@Component
public class DoorMarhsaller implements ConcrateMarshaller<DoorDTO, Door> {
    private ItemStoredMarshaller itemStoredMarshaller;
    @Autowired
    public DoorMarhsaller(ItemStoredMarshaller itemStoredMarshaller) {
        this.itemStoredMarshaller = itemStoredMarshaller;
    }

    @Override
    public Door marshall(DoorDTO dtoInstance) {
        Door businessDoor = new Door();
        businessDoor.setOpen(dtoInstance.isOpen());
        businessDoor.setKeyItem(itemStoredMarshaller.marshall(dtoInstance.getKeyItem()));
        return businessDoor;
    }

    @Override
    public DoorDTO marshall(Door businessInstance) {
        DoorDTO dtoDoor = new DoorDTO();
        dtoDoor.setOpen(businessInstance.isOpen());
        dtoDoor.setKeyItem(itemStoredMarshaller.marshall(businessInstance.getKeyItem()));
        return dtoDoor;
    }
}
