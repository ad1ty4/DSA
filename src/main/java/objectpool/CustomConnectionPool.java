package objectpool;

public class CustomConnectionPool extends ConnectionPool<Connection> {

    public CustomConnectionPool() {
        super();
    }

    @Override
    Connection create() {
        return new Connection();
    }

    @Override
    boolean isExpired(Connection connection) {
        return false;
    }

}
