package service;


import java.util.List;

public interface InterfaceService<T> {

    List<T> findAll();

    void add(T newInstant);

    void removeById(long id);

    void update(T newInstant);

    boolean existsById(long id);

    T findById(long id);
}
