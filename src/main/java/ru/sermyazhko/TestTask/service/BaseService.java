package ru.sermyazhko.TestTask.service;

import ru.sermyazhko.TestTask.service.exception.EntityNotFound;

import java.util.List;

public interface BaseService<T, K>{
    K addEntity(T entity) throws EntityNotFound;
    List<T> readAllEntity();
    T readEntityById(K id) throws EntityNotFound;
    K updateEntityById(T updateEntity, K id) throws EntityNotFound;
    Boolean deleteEntityById(K id);
}
