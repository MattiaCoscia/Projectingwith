package pawtropolis.utility.marshall;

public interface ConcrateMarshaller<A,B> {
    B marshall(A dtoInstance);
    A marshall(B businessInstance);
}
