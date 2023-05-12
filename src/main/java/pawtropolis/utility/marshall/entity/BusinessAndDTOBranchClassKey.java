package pawtropolis.utility.marshall.entity;

import lombok.Getter;
import pawtropolis.persistence.dto.entity.EntityDTO;

import java.util.Objects;
@Getter
public class BusinessAndDTOBranchClassKey<A extends EntityDTO, B> {
    private final Class<A> dtoClass;
    private final Class<B> businessClass;
    private final int hashCode;

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
        BusinessAndDTOBranchClassKey<A,B> that = (BusinessAndDTOBranchClassKey<A,B>) obj;
        return that.businessClass.isAssignableFrom(this.businessClass) || that.dtoClass.isAssignableFrom(this.dtoClass);
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
