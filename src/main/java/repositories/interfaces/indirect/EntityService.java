package repositories.interfaces.indirect;

public interface EntityService<V> {
    void add(V entity);

    void update(V entity);

    void remove(V entity);

}
