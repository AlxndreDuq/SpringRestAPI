package com.jeeProject.TaskManager.Controller;

import com.jeeProject.TaskManager.DTOs.TaskDTO.TaskCreateDTO;
import com.jeeProject.TaskManager.DTOs.TaskDTO.TaskReadUpdateDTO;
import com.jeeProject.TaskManager.Service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Créer une tâche", description = "Crée une tâche.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tâche créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content),
            @ApiResponse(responseCode = "500", description = "Tâche non créé"),
    })
    @PostMapping("/create")
    public TaskReadUpdateDTO create(@Valid @RequestBody TaskCreateDTO dto) {
        return taskService.createTask(dto);
    }

    @Operation(summary = "Mettre à jour une tâche", description = "Met à jour les données d'une tâche existante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tâche mis à jour"),
            @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content),
            @ApiResponse(responseCode = "500", description = "Tâche non mis à jour", content = @Content)
    })
    @PutMapping("/update")
    public TaskReadUpdateDTO update(@Valid @RequestBody TaskReadUpdateDTO dto) {
        return taskService.updateTask(dto);
    }


    @Operation(summary = "Supprimer une tâche", description = "Supprime une tâche à partir de son ID.")
    @ApiResponse(responseCode = "200", description = "Tâche supprimée avec succès")
    @DeleteMapping("/delete/{id}")
    public boolean delete(@Parameter @PathVariable Long id) {
        taskService.deleteTask(id);
        return true;
    }

    @Operation(summary = "Lister tous les tâches", description = "Retourne la liste de tous les tâches.")
    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès")
    @GetMapping("/all")
    public List<TaskReadUpdateDTO> getAll() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Obtenir une tâche par ID", description = "Retourne une tâche correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tâche trouvé"),
            @ApiResponse(responseCode = "500", description = "Tâche non trouvé"),
    })
    @GetMapping("/{id}")
    public TaskReadUpdateDTO getById(@Parameter @PathVariable Long id) {
        return taskService.getTaskById(id);
    }
}
