package com.jeeProject.TaskManager.DTOs.UserDTO;

import com.jeeProject.TaskManager.DTOs.BaseDTO;
import com.jeeProject.TaskManager.Entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsersCreateDTO extends BaseDTO<Users> {
    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    private String name;

    @NotBlank(message = "L'email de l'utilisateur est obligatoire")
    @Email(message = "Le format de l'email doit être correct")
    private String email;

    public UsersCreateDTO() {}

    public UsersCreateDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public Users toEntity() {
        return new Users(this.name, this.email);
    }

    public @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Le nom d'utilisateur ne peut pas être vide") String name) {
        this.name = name;
    }

    public @NotBlank(message = "L'email de l'utilisateur est obligatoire") @Email(message = "Le format de l'email doit être correct") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "L'email de l'utilisateur est obligatoire") @Email(message = "Le format de l'email doit être correct") String email) {
        this.email = email;
    }
}
