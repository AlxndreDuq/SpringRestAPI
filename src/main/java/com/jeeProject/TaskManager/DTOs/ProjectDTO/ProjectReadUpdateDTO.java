package com.jeeProject.TaskManager.DTOs.ProjectDTO;

import com.jeeProject.TaskManager.DTOs.BaseDTO;
import com.jeeProject.TaskManager.Entity.Project;
import com.jeeProject.TaskManager.Entity.Task;
import com.jeeProject.TaskManager.Entity.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class ProjectReadUpdateDTO extends BaseDTO<Project> {

    @NotNull(message = "L'identifiant du projet est requis.")
    private Long id;
    @NotBlank(message = "Le nom du projet est requis.")
    private String name;
    private String description;
    @NotNull(message = "La date de création du projet est requise.")
    private Date createdAt;
    @NotNull(message = "L'utilisateur du projet est requis.")
    private Users owner;
    private List<Task> tasks;

    public ProjectReadUpdateDTO() {}

    public ProjectReadUpdateDTO(Long id, String name, String description, Date createdAt, Users owner, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.owner = owner;
        this.tasks = tasks;
    }

    public @NotNull(message = "L'identifiant du projet est requis.") Long getId() {
        return id;
    }

    public @NotBlank(message = "Le nom du projet est requis.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Le nom du projet est requis.") String name) {
        this.name = name;
    }

    public @NotNull(message = "La date de création du projet est requise.") Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NotNull(message = "La date de création du projet est requise.") Date createdAt) {
        this.createdAt = createdAt;
    }

    public @NotNull(message = "L'utilisateur du projet est requis.") Users getOwner() {
        return owner;
    }

    public void setOwner(@NotNull(message = "L'utilisateur du projet est requis.") Users owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public Project toEntity() {
        return new Project(id, name, description, createdAt, owner, tasks);
    }

    public static ProjectReadUpdateDTO fromEntity(Project project) {
        return new ProjectReadUpdateDTO(
                project.getId_project(),
                project.getName(),
                project.getDescription(),
                project.getCreatedAt(),
                project.getOwner(),
                project.getTasks()
        );
    }

    public static List<ProjectReadUpdateDTO> fromEntityList(List<Project> projects) {
        return projects.stream()
                .map(ProjectReadUpdateDTO::fromEntity)
                .toList();
    }

    public static List<Project> toEntityList(List<ProjectReadUpdateDTO> projects) {
        return projects.stream()
                .map(ProjectReadUpdateDTO::toEntity)
                .toList();
    }

}
