package com.jeeProject.TaskManager.DTOs.TaskDTO;

import com.jeeProject.TaskManager.DTOs.BaseDTO;
import com.jeeProject.TaskManager.Entity.Project;
import com.jeeProject.TaskManager.Entity.Task;
import com.jeeProject.TaskManager.Entity.TaskStatus;
import com.jeeProject.TaskManager.Entity.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskCreateDTO extends BaseDTO<Task> {

    @NotBlank(message = "Le titre de la tâche est obligatoire")
    private String title;

    private String description;

    @NotNull(message = "Le statut est obligatoire")
    private TaskStatus status;

    private Users assignedTo;

    @NotNull(message = "Le projet est obligatoire")
    private Project project;

    public TaskCreateDTO() {}

    public TaskCreateDTO(String title, String description, TaskStatus status, Users assignedTo, Project project) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.project = project;
    }

    public @NotBlank(message = "Le titre de la tâche est obligatoire") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Le titre de la tâche est obligatoire") String title) {
        this.title = title;
    }

    public @NotNull(message = "Le statut est obligatoire") TaskStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Le statut est obligatoire") TaskStatus status) {
        this.status = status;
    }

    public @NotNull(message = "Le projet est obligatoire") Project getProject() {
        return project;
    }

    public void setProject(@NotNull(message = "Le projet est obligatoire") Project project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Users assignedTo) {
        this.assignedTo = assignedTo;
    }


    @Override
    public Task toEntity() {
        return new Task(title, description, status, assignedTo, project);
    }
}
