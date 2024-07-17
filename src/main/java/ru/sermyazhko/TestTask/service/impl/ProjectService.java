package ru.sermyazhko.TestTask.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sermyazhko.TestTask.dto.ProjectDTO;
import ru.sermyazhko.TestTask.model.Project;
import ru.sermyazhko.TestTask.repository.ProjectRepository;
import ru.sermyazhko.TestTask.service.BaseService;
import ru.sermyazhko.TestTask.service.exception.EntityNotFound;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService implements BaseService<ProjectDTO, Long> {
    private final ProjectRepository repository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Long addEntity(ProjectDTO entity) {
        Project project = new Project();
        project.setName(entity.getName());
        project.setDescription(entity.getDescription());
        project.setStatus(entity.getStatus());
        project.setEndDate(entity.getEndDate());
        project.setStartDate(entity.getStartDate());

        return repository.save(project).getId();
    }

    @Override
    public List<ProjectDTO> readAllEntity() {
        return repository.findAll().stream()
                .map(a -> modelMapper.map(a, ProjectDTO.class))
                .toList();
    }

    @Override
    public ProjectDTO readEntityById(Long id) throws EntityNotFound {
        Project project = repository.findById(id).orElseThrow(
                () -> new EntityNotFound("project with " + id + " id not found")
        );

        return modelMapper.map(project, ProjectDTO.class);
    }

    @Override
    public Long updateEntityById(ProjectDTO updateEntity, Long id) throws EntityNotFound {
        Project project = repository.findById(id).orElseThrow(
                () -> new EntityNotFound("project with " + id + " id not found")
        );

        project.setName(updateEntity.getName() != null ? updateEntity.getName() : project.getName());
        project.setDescription(updateEntity.getDescription() != null ? updateEntity.getDescription() : project.getDescription());
        project.setStatus(updateEntity.getStatus() != null ? updateEntity.getStatus() : project.getStatus());
        project.setEndDate(updateEntity.getEndDate() != null ? updateEntity.getEndDate() : project.getEndDate());
        project.setStartDate(updateEntity.getStartDate() != null ? updateEntity.getStartDate() : project.getStartDate());

        return repository.save(project).getId();
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
