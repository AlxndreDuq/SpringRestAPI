package com.jeeProject.TaskManager.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {

    @GeneratedValue
    @Id
    private Long id_project;

    @NotBlank(message = "Le nom du projet ne peut pas être vide")
    private String name;

    private String description;

    @NotNull(message = "la date de création du projet ne peut pas être vide")
    private Date createdAt;

    @NotNull(message = "L'utilisateur du projet ne peut pas être vide")
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users owner;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();

    public Project(String name, String description, Date createdAt, Users owner, List<Task> tasks) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.owner = owner;
        this.tasks = tasks;
    }

    public Project(Long id_project, String name, String description, Date createdAt, Users owner, List<Task> tasks) {
        this.id_project = id_project;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.owner = owner;
        this.tasks = tasks;
    }

    public Project() {}

    public Long getId_project() {
        return id_project;
    }

    public @NotBlank(message = "Le nom du projet ne peut pas être vide") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Le nom du projet ne peut pas être vide") String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull(message = "la date de création du projet ne peut pas être vide") Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NotNull(message = "la date de création du projet ne peut pas être vide") Date createdAt) {
        this.createdAt = createdAt;
    }

    public @NotNull(message = "L'utilisateur du projet ne peut pas être vide") Users getOwner() {
        return owner;
    }

    public void setOwner(@NotNull(message = "L'utilisateur du projet ne peut pas être vide") Users owner) {
        this.owner = owner;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
