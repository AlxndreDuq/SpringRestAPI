package com.jeeProject.TaskManager.Controller;

import com.jeeProject.TaskManager.DTOs.UserDTO.UsersCreateDTO;
import com.jeeProject.TaskManager.DTOs.UserDTO.UsersReadUpdateDTO;
import com.jeeProject.TaskManager.Entity.Users;
import com.jeeProject.TaskManager.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Créer un utilisateur", description = "Crée un utilisateur.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content),
            @ApiResponse(responseCode = "500", description = "Utilisateur non créé", content = @Content),
    })
    @PostMapping("/create")
    public UsersReadUpdateDTO create(@Valid @RequestBody UsersCreateDTO u) {
        return userService.createUser(u);
    }


    @Operation(summary = "Mettre à jour un utilisateur", description = "Met à jour les données d'un utilisateur existant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur mis à jour"),
            @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content),
            @ApiResponse(responseCode = "500", description = "Utilisateur non mis à jour", content = @Content)
    })
    @PutMapping("/update")
    public UsersReadUpdateDTO update(@Valid @RequestBody UsersReadUpdateDTO u) {
        return userService.updateUser(u);
    }


    @Operation(summary = "Supprimer un utilisateur", description = "Supprime un utilisateur à partir de son ID.")
    @ApiResponse(responseCode = "200", description = "Utilisateur supprimé avec succès")
    @DeleteMapping("/delete/{id}")
    public boolean delete(@Parameter @PathVariable Long id) {
        userService.deleteUser(id);
        return true;
    }

    @Operation(summary = "Lister tous les utilisateurs", description = "Retourne la liste de tous les utilisateurs.")
    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès")
    @GetMapping("/all")
    public List<UsersReadUpdateDTO> getAll() {
        return userService.getUsers();
    }

    @Operation(summary = "Obtenir un utilisateur par ID", description = "Retourne un utilisateur correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé"),
            @ApiResponse(responseCode = "400", description = "Utilisateur non trouvé", content = @Content),
    })
    @GetMapping("/{id}")
    public UsersReadUpdateDTO getById(@Parameter @PathVariable Long id) {
        return userService.getUserById(id);
    }
}

