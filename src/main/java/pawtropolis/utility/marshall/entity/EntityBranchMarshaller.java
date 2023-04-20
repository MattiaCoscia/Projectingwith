package pawtropolis.utility.marshall.entity;

import lombok.Getter;
import pawtropolis.model.ConcrateClasses;
import pawtropolis.model.dto.DTOClasses;
@Getter
public abstract class EntityBranchMarshaller<C extends EntityBranchMarshaller<C,A,B>,A extends DTOClasses, B extends ConcrateClasses> {
    private BusinessAndDTOBranchClassKey doubleKey;
    public EntityBranchMarshaller(Class<B> branchBusinessClass, Class<A> branchDTOClass) {
        this.doubleKey = new BusinessAndDTOBranchClassKey(branchBusinessClass,branchDTOClass);
    }

    public abstract <D extends A, E extends B > E marshall(D animalDTO);

    public abstract <D extends A, E extends B > D marshall(E animal);
}
