package ru.sermyazhko.TestTask.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T, K>{
    ResponseEntity<?> add(T entity);
    ResponseEntity<?> readAll();
    ResponseEntity<?> read(K id);
    ResponseEntity<?> update(T updateEntity, K id);
    ResponseEntity<Boolean> delete(K id);
}
