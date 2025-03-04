package ru.sermyazhko.TestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sermyazhko.TestTask.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
