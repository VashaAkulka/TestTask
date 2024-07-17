package ru.sermyazhko.TestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sermyazhko.TestTask.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
