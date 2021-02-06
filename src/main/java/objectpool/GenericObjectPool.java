package objectpool;

/**
 * Client can get initialize object pool,
 * Client can get object from pool
 * client can put object back in pool
 * if client puts wrong object in pool exception arizes
 *
 *
 * @param <E>
 */


public interface GenericObjectPool<E> {
    E getObject() throws NoObjectAvailableException;
    void putObject(E e) throws WrongObjectTypeException;
}
