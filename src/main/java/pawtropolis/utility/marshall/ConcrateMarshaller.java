package pawtropolis.utility.marshall;

import pawtropolis.model.BusinessClass;
import pawtropolis.model.dto.DTOClass;

public interface ConcrateMarshaller<A extends DTOClass,B extends BusinessClass> {
    B marshall(A dtoInstance);
    A marshall(B businessInstance);
}
