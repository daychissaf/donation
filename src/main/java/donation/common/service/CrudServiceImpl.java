package donation.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class CrudServiceImpl<T> implements CrudService<T> {

    @Autowired
    CrudRepository<T, Long> crudRepository;

    @Autowired
    ObjectUpdater objectUpdater;

    @Override
    public T add(T object) {
        return crudRepository.save(object);
    }

    @Override
    public T update(T objectToUpdate, T newObjectData) {
        try {
            objectUpdater.update(objectToUpdate, newObjectData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crudRepository.save(objectToUpdate);
    }

    @Override
    public void delete(Long id) {
        crudRepository.delete(id);
    }

    @Override
    public List<T> getAll() {
        return (List<T>) crudRepository.findAll();
    }

    @Override
    public T getById(Long id) {
        return crudRepository.findOne(id);
    }
}
