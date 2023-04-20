package pawtropolis.utility.marshall.entity;

import pawtropolis.model.ConcrateClasses;
import pawtropolis.model.dto.DTOClasses;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;
import pawtropolis.utility.marshall.entity.animal.BusinessAndDTOAnimalClassKey;

import java.util.Objects;

public class BusinessAndDTOBranchClassKey<A extends DTOClasses, B extends ConcrateClasses> {
    private Class<A> dtoClass;
    private Class<B> businessClass;
    private int hashCode;

    public BusinessAndDTOBranchClassKey(Class<B> businessClass, Class<A> dtoClass) {
        this.businessClass = businessClass;
        this.dtoClass = dtoClass;
        this.hashCode = Objects.hash(businessClass, dtoClass);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        BusinessAndDTOBranchClassKey that = (BusinessAndDTOBranchClassKey) obj;
        return that.businessClass.isAssignableFrom(this.businessClass) || that.dtoClass.isAssignableFrom(this.dtoClass);
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
