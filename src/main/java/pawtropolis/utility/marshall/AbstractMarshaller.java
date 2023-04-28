package pawtropolis.utility.marshall;

import pawtropolis.model.BusinessClass;
import pawtropolis.model.dto.DTOClass;

public interface AbstractMarshaller<A extends DTOClass,B extends BusinessClass> {
    B marshallFromDTO(A dtoInstance, Class<B> businessClass);
    A marshallToDTO(B businessInstance, Class<A> dtoClass);
}
