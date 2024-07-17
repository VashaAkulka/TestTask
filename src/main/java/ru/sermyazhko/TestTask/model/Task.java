package ru.sermyazhko.TestTask.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String assignedTo;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
