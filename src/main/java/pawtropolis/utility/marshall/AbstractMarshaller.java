package pawtropolis.utility.marshall;

public interface AbstractMarshaller<A,B> {
    B marshallFromDTO(A dtoInstance, Class<B> businessClass);
    A marshallToDTO(B businessInstance, Class<A> dtoClass);
}
