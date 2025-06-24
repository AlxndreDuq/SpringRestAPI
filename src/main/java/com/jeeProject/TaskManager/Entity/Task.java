package com.jeeProject.TaskManager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id_task;

    @NotBlank(message = "Le titre de la tâche est obligatoire")
    private String title;

    private String description;

    @NotNull(message = "Le statut de la tâche est obligatoire")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users assignedTo;

    @NotNull(message = "Le projet de la tache est obligatoire")
    @ManyToOne
    @JoinColumn(name = "id_project")
    @JsonBackReference
    private Project project;

    public Task(Long id_task, String title, String description, TaskStatus status, Users assignedTo, Project project) {
        this.id_task = id_task;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.project = project;
    }

    public Task(String title, String description, TaskStatus status, Users assignedTo, Project project) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.project = project;
    }

    public Task() {}

    public Long getId_task() {
        return id_task;
    }

    public @NotBlank(message = "Le titre de la tâche est obligatoire") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Le titre de la tâche est obligatoire") String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull(message = "Le statut de la tâche est obligatoire") TaskStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Le statut de la tâche est obligatoire") TaskStatus status) {
        this.status = status;
    }

    public Users getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Users assignedTo) {
        this.assignedTo = assignedTo;
    }

    public @NotNull(message = "Le projet de la tache est obligatoire") Project getProject() {
        return project;
    }

    public void setProject(@NotNull(message = "Le projet de la tache est obligatoire") Project project) {
        this.project = project;
    }

}