package ru.sermyazhko.TestTask.dto;

import lombok.Data;
import ru.sermyazhko.TestTask.model.Status;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private String assignedTo;
    private Long projectId;
    private Status status;
}
