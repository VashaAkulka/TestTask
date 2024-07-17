package ru.sermyazhko.TestTask.dto;

import lombok.Data;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private String assignedTo;
    private Long projectId;
}
