package objectpool;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Queue;

public class GenericObjectPoolImpl<E> implements GenericObjectPool<E> {

    private PoolObject<E>[] pool;
    private Queue<PoolObject<E>> freeObjects;

    public static class PoolObject<E>{
        E object;
        boolean isBusy;
        public PoolObject(E object) {
            this.object = object;
        }
    }

    public GenericObjectPoolImpl(int size,Class<E> type) throws IllegalAccessException, InstantiationException {
        pool = new PoolObject[size];
        freeObjects = new LinkedList<>();
        for (int i = 0; i <size ; i++) {
            pool[i] = new PoolObject<>(getTypeParameterClass().newInstance());
            freeObjects.add(pool[i]);
        }
    }

    public Class<E> getTypeParameterClass()
    {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<E>) paramType.getActualTypeArguments()[0];
    }


    @Override
    public E getObject() throws NoObjectAvailableException {
        if(freeObjects.isEmpty())
            throw new NoObjectAvailableException();
        return freeObjects.poll().object;
    }

    @Override
    public void putObject(E e) throws WrongObjectTypeException {

    }
}
