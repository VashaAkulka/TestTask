package ru.sermyazhko.TestTask.dto;

import lombok.Data;
import ru.sermyazhko.TestTask.model.Status;

import java.time.LocalDate;

@Data
public class ProjectDTO {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
}
