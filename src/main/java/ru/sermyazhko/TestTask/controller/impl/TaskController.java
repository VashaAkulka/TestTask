package ru.sermyazhko.TestTask.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sermyazhko.TestTask.controller.BaseController;
import ru.sermyazhko.TestTask.dto.TaskDTO;
import ru.sermyazhko.TestTask.service.exception.EntityNotFound;
import ru.sermyazhko.TestTask.service.impl.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController implements BaseController<TaskDTO, Long> {
    private final TaskService service;

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> read(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.readEntityById(id));
        } catch (EntityNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> readAll() {
        List<TaskDTO> taskDTOList = service.readAllEntity();
        if (taskDTOList.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else return ResponseEntity.ok(taskDTOList);
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody TaskDTO entity) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addEntity(entity));
        } catch (EntityNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@RequestBody TaskDTO updateEntity, @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.updateEntityById(updateEntity, id));
        } catch (EntityNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        Boolean delete = service.deleteEntityById(id);
        if (delete) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
        else return ResponseEntity.status(HttpStatus.NO_CONTENT).body(false);
    }
}
