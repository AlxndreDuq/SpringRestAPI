package com.jeeProject.TaskManager.Controller;

import com.jeeProject.TaskManager.DTOs.ProjectDTO.ProjectCreateDTO;
import com.jeeProject.TaskManager.DTOs.ProjectDTO.ProjectReadUpdateDTO;
import com.jeeProject.TaskManager.Entity.Project;
import com.jeeProject.TaskManager.Entity.Users;
import com.jeeProject.TaskManager.Service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "Créer un projet", description = "Crée un projet.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projet créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content),
            @ApiResponse(responseCode = "500", description = "Projet non créé"),
    })
    @PostMapping("/create")
    public ProjectReadUpdateDTO create(@Valid @RequestBody ProjectCreateDTO p) {
        return projectService.createProject(p);
    }

    @Operation(summary = "Mettre à jour un projet", description = "Met à jour les données d'un projet existant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projet mis à jour"),
            @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content),
            @ApiResponse(responseCode = "500", description = "Projet non mis à jour", content = @Content)
    })
    @PutMapping("/update")
    public ProjectReadUpdateDTO update(@Valid @RequestBody ProjectReadUpdateDTO p) {
        return projectService.updateProject(p);
    }

    @Operation(summary = "Supprimer un projet", description = "Supprime un projet à partir de son ID.")
    @ApiResponse(responseCode = "200", description = "Projet supprimé avec succès")
    @DeleteMapping("/delete/{id}")
    public boolean delete(@Parameter @PathVariable Long id) {
        projectService.deleteProject(id);
        return true;
    }

    @Operation(summary = "Lister tous les projets", description = "Retourne la liste de tous les projets.")
    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès")
    @GetMapping("/all")
    public List<ProjectReadUpdateDTO> getAll() {
        return projectService.getAllProjects();
    }

    @Operation(summary = "Obtenir un projet par ID", description = "Retourne un projet correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projet trouvé"),
            @ApiResponse(responseCode = "500", description = "Projet non trouvé"),
    })
    @GetMapping("/{id}")
    public ProjectReadUpdateDTO getById(@Parameter @PathVariable Long id) {
        return projectService.getProjectById(id);
    }
}
