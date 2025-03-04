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
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.readEntityById(id));
        } catch (EntityNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with ID " + id + " not found");
        }
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        List<TaskDTO> taskDTOList = service.readAllEntity();
        if (taskDTOList.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Empty list");
        else return ResponseEntity.ok(taskDTOList);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TaskDTO entity) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addEntity(entity));
        } catch (EntityNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with ID " + entity.getProjectId() + " not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TaskDTO updateEntity, @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.updateEntityById(updateEntity, id));
        } catch (EntityNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity with ID " + id + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        Boolean delete = service.deleteEntityById(id);
        if (delete) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
