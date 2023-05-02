package pawtropolis.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.model.BusinessClass;
import pawtropolis.model.dto.DTOClass;
import pawtropolis.utility.marshall.ConcrateMarshaller;

public abstract class AbstractPersistanceClass<A extends DTOClass,B extends BusinessClass, I extends Number> {
    private JpaRepository<A,I> repository;
    private ConcrateMarshaller<A,B> marshaller;

    protected AbstractPersistanceClass(JpaRepository<A,I> repository,ConcrateMarshaller<A,B> marshaller){
        this.repository = repository;
        this.marshaller = marshaller;
    }

    public abstract B save(A dto);
    public abstract B edit(A dto);
    public abstract boolean remove(I id);
    public abstract B get(I id);
}
