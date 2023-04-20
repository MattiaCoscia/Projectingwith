package pawtropolis.utility.marshall.animal;

import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;

import java.util.Objects;

public class BusinessAndDTOClassKey {
    private Class<? extends Animal> businessClass;
    private Class<? extends AnimalDTO> dtoClass;
    private int hashCode;

    public BusinessAndDTOClassKey(Class<? extends Animal> businessClass, Class<? extends AnimalDTO> dtoClass){
        this.businessClass = businessClass;
        this.dtoClass = dtoClass;
        this.hashCode = Objects.hash(businessClass,dtoClass);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        BusinessAndDTOClassKey that = (BusinessAndDTOClassKey) obj;
        return that.businessClass.equals(this.businessClass) || that.dtoClass.equals(this.dtoClass);
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
