package com.jeeProject.TaskManager.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Users {

    @GeneratedValue
    @Id
    private Long id_user;

    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    private String name;

    @NotBlank(message = "L'email de l'utilisateur est obligatoire")
    @Email(message = "Le format de l'email doit être correct")
    private String email;

    public Users(Long id_user, String name, String email) {
        this.id_user = id_user;
        this.name = name;
        this.email = email;
    }

    public Users(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Users() {}

    public Long getId_user() {
        return id_user;
    }

    public @NotBlank(message = "Le nom d'utilisateur est obligatoire") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Le nom d'utilisateur est obligatoire") String name) {
        this.name = name;
    }

    public @NotBlank(message = "L'email de l'utilisateur est obligatoire") @Email(message = "Le format de l'email doit être correct") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "L'email de l'utilisateur est obligatoire") @Email(message = "Le format de l'email doit être correct") String email) {
        this.email = email;
    }
}
