package com.jeeProject.TaskManager.DTOs.ProjectDTO;

import com.jeeProject.TaskManager.DTOs.BaseDTO;
import com.jeeProject.TaskManager.Entity.Project;
import com.jeeProject.TaskManager.Entity.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class ProjectCreateDTO extends BaseDTO<Project> {

    @NotBlank(message = "Le nom du projet est obligatoire")
    private String name;

    private String description;

    @NotNull(message = "L'identifiant du propriétaire est obligatoire")
    private Users owner;

    public ProjectCreateDTO() {}

    public ProjectCreateDTO(String name, String description, Users owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public @NotBlank(message = "Le nom du projet est obligatoire") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Le nom du projet est obligatoire") String name) {
        this.name = name;
    }

    public @NotNull(message = "L'identifiant du propriétaire est obligatoire") Users getOwner() {
        return owner;
    }

    public void setOwner(@NotNull(message = "L'identifiant du propriétaire est obligatoire") Users owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Project toEntity() {
        return new Project(name, description, new Date(), owner, null);
    }
}