package ru.sermyazhko.TestTask.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sermyazhko.TestTask.dto.TaskDTO;
import ru.sermyazhko.TestTask.model.Task;
import ru.sermyazhko.TestTask.repository.ProjectRepository;
import ru.sermyazhko.TestTask.repository.TaskRepository;
import ru.sermyazhko.TestTask.service.BaseService;
import ru.sermyazhko.TestTask.service.exception.EntityNotFound;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements BaseService<TaskDTO, Long> {
    private final TaskRepository repository;
    private final ProjectRepository projectRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Long addEntity(TaskDTO entity) throws EntityNotFound {
        Task task = new Task();
        task.setTitle(entity.getTitle());
        task.setDescription(entity.getDescription());
        task.setAssignedTo(entity.getAssignedTo());
        task.setProject(projectRepository.findById(entity.getProjectId()).orElseThrow(
                () -> new EntityNotFound("project with " + entity.getProjectId() + " id not found"))
        );

        return repository.save(task).getId();
    }

    @Override
    public List<TaskDTO> readAllEntity() {
        return repository.findAll().stream()
                .map(a -> modelMapper.map(a, TaskDTO.class))
                .toList();
    }

    @Override
    public TaskDTO readEntityById(Long id) throws EntityNotFound {
        Task task = repository.findById(id).orElseThrow(
                () -> new EntityNotFound("task with " + id + " id not found")
        );

        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public Long updateEntityById(TaskDTO updateEntity, Long id) throws EntityNotFound {
        Task task = repository.findById(id).orElseThrow(
                () -> new EntityNotFound("task with " + id + " id not found")
        );

        task.setTitle(updateEntity.getTitle() != null ? updateEntity.getTitle() : task.getTitle());
        task.setDescription(updateEntity.getDescription() != null ? updateEntity.getDescription() : task.getDescription());
        task.setAssignedTo(updateEntity.getAssignedTo() != null ? updateEntity.getAssignedTo() : task.getAssignedTo());

        if (updateEntity.getProjectId() != null) {
            task.setProject(projectRepository.findById(updateEntity.getProjectId()).orElseThrow(
                    () -> new EntityNotFound("project with " + updateEntity.getProjectId() + " id not found"))
            );
        }

        return repository.save(task).getId();
    }

    @Override
    public Boolean deleteEntityById(Long id) {
        try {
            readEntityById(id);

            repository.deleteById(id);
            return true;
        } catch (EntityNotFound e) {
            return false;
        }
    }
}
