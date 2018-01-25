package donation.common.controller;


import donation.common.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class CrudController<T> {

    public abstract CrudService<T> service();

    @PostMapping
    public T add(@Valid @RequestBody T object) {
        return service().add(object);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody T newObjectData) {
        T objectToUpdate = service().getById(id);
        if (objectToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service().update(objectToUpdate, newObjectData), HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public List<T> list() {
        return service().getAll();
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        service().delete(id);
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable Long id) {
        return service().getById(id);
    }

}
