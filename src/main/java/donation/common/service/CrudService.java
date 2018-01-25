package donation.common.service;

import java.util.List;

public interface CrudService<T> {

    T add(T object);

    T update(T objectToUpdate, T newObjectData);

    void delete(Long id);

    List<T> getAll();

    T getById(Long id);
}
