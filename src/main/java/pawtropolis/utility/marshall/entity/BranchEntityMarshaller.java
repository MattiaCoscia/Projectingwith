package pawtropolis.utility.marshall.entity;

import lombok.Getter;
import pawtropolis.model.BusinessClass;
import pawtropolis.model.dto.DTOClass;
@Getter
public abstract class BranchEntityMarshaller<A extends DTOClass, B extends BusinessClass> {
    private final BusinessAndDTOBranchClassKey<A,B> doubleKey;
    protected BranchEntityMarshaller(Class<B> branchBusinessClass, Class<A> branchDTOClass) {
        this.doubleKey = new BusinessAndDTOBranchClassKey<>(branchBusinessClass,branchDTOClass);
    }
    public Class<A> getDTOClass(){
        return doubleKey.getDtoClass();
    }
    public Class<B> getBusinessClass(){
        return doubleKey.getBusinessClass();
    }
    public abstract <D extends A, E extends B > E marshall(D animalDTO);

    public abstract <D extends A, E extends B > D marshall(E animal);
}
