package ru.sermyazhko.TestTask.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T, K>{
    ResponseEntity<K> add(T entity);
    ResponseEntity<List<T>> readAll();
    ResponseEntity<T> read(K id);
    ResponseEntity<K> update(T updateEntity, K id);
    ResponseEntity<Boolean> delete(K id);
}
