package objectpool;

public class Driver {
    public static void main(String[] args) {
        ConnectionPool objectPool = new CustomConnectionPool();
        objectPool.putBack(objectPool.take());
    }
}
