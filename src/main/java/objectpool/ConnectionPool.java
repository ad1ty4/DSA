package objectpool;

import java.util.*;

/**
 *
 * Can get and put object in pool
 * if no object present -> create object
 * object has an expiration time
 * if object is expired -> delete from object pool
 *
 * @param <T>
 */

public abstract class ConnectionPool<T> {

    private Map<T,Long> available,unavailable;

    public ConnectionPool() {
        this.available = new HashMap<>();
        this.unavailable = new HashMap<>();
    }

    void putBack(T t){
        unavailable.remove(t);
        available.put(t,System.currentTimeMillis());
    }

    void remove(T t){
        available.remove(t);
    }

    T take(){
        for (T t : available.keySet()) {
            if (isExpired(t)) {
                remove(t);
            } else {
                available.remove(t);
                unavailable.put(t, System.currentTimeMillis());
                return t;
            }
        }
        T t = create();
        unavailable.put(t,System.currentTimeMillis());
        return t;
    }

    abstract T create();

    abstract boolean isExpired(T t);
}
