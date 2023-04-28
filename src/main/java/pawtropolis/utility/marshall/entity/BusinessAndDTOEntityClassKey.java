package pawtropolis.utility.marshall.entity;

import lombok.Getter;
import pawtropolis.model.dto.entity.EntityDTO;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.npc.animal.category.Animal;

import java.util.Objects;
@Getter
public class BusinessAndDTOEntityClassKey<A extends EntityDTO,B extends Entity> {
    private final Class<B> businessClass;
    private final Class<A> dtoClass;
    private final int hashCode;

    public BusinessAndDTOEntityClassKey(Class<B> businessClass, Class<A> dtoClass){
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
        BusinessAndDTOEntityClassKey<A,B> that = (BusinessAndDTOEntityClassKey<A,B>) obj;
        return that.businessClass.equals(this.businessClass) || that.dtoClass.equals(this.dtoClass);
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
