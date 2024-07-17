package ru.sermyazhko.TestTask.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sermyazhko.TestTask.controller.BaseController;
import ru.sermyazhko.TestTask.dto.ProjectDTO;
import ru.sermyazhko.TestTask.service.exception.EntityNotFound;
import ru.sermyazhko.TestTask.service.impl.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController implements BaseController<ProjectDTO, Long> {
    private final ProjectService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> read(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.readEntityById(id));
        } catch (EntityNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> readAll() {
        List<ProjectDTO> projectDTOList = service.readAllEntity();
        if (projectDTOList.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else return ResponseEntity.ok(projectDTOList);
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody ProjectDTO entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addEntity(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@RequestBody ProjectDTO updateEntity, @PathVariable("id") Long id) {
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
