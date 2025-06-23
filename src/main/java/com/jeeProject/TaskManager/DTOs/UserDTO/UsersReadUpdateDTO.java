package com.jeeProject.TaskManager.DTOs.UserDTO;

import com.jeeProject.TaskManager.DTOs.BaseDTO;
import com.jeeProject.TaskManager.Entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class UsersReadUpdateDTO extends BaseDTO<Users> {
    @NotNull(message = "L'identifiant de l'utilisateur est requis.")
    private Long id_user;

    @NotBlank(message = "Le nom de l'utilisateur ne peut pas être vide.")
    private String name;

    @NotBlank(message = "L'email de l'utilisateur est obligatoire")
    @Email(message = "Le format de l'email doit être correct")
    private String email;

    public UsersReadUpdateDTO(Long id_user, String name, String email) {
        this.id_user = id_user;
        this.name = name;
        this.email = email;
    }

    public UsersReadUpdateDTO() {}

    @Override
    public Users toEntity() {
        return new Users(this.id_user, this.name, this.email);
    }

    public static UsersReadUpdateDTO fromEntity(Users user) {
        return new UsersReadUpdateDTO(user.getId_user(), user.getName(), user.getEmail());
    }

    public static List<UsersReadUpdateDTO> fromEntityList(List<Users> users) {
        return users.stream()
                .map(UsersReadUpdateDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public static List<Users> toEntityList(List<UsersReadUpdateDTO> users) {
        return users.stream()
                .map(UsersReadUpdateDTO::toEntity)
                .collect(Collectors.toList());
    }

    public @NotNull(message = "L'identifiant de l'utilisateur est requis.") Long getId_user() {
        return id_user;
    }


    public @NotBlank(message = "Le nom de l'utilisateur ne peut pas être vide.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Le nom de l'utilisateur ne peut pas être vide.") String name) {
        this.name = name;
    }

    public @NotBlank(message = "L'email de l'utilisateur est obligatoire") @Email(message = "Le format de l'email doit être correct") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "L'email de l'utilisateur est obligatoire") @Email(message = "Le format de l'email doit être correct") String email) {
        this.email = email;
    }
}
