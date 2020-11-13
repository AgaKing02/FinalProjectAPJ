package repositories.interfaces.indirect;

import java.util.List;

public interface EntityRepository<T> {
    void add(T entity);

    void update(T entity);

    void remove(T entity);

    List<T> query(String sql);

    T queryOne(String sql);
}
