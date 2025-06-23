package com.jeeProject.TaskManager.Service;

import com.jeeProject.TaskManager.DTOs.ProjectDTO.ProjectCreateDTO;
import com.jeeProject.TaskManager.DTOs.ProjectDTO.ProjectReadUpdateDTO;
import com.jeeProject.TaskManager.Entity.Project;
import com.jeeProject.TaskManager.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectReadUpdateDTO createProject(ProjectCreateDTO dto) {
        return ProjectReadUpdateDTO.fromEntity(
                projectRepository.save(dto.toEntity())
        );
    }

    public ProjectReadUpdateDTO updateProject(ProjectReadUpdateDTO dto) {
        return ProjectReadUpdateDTO.fromEntity(
                projectRepository.save(dto.toEntity())
        );
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<ProjectReadUpdateDTO> getAllProjects() {
        return ProjectReadUpdateDTO.fromEntityList(projectRepository.findAll());
    }

    public ProjectReadUpdateDTO getProjectById(Long id) {
        return ProjectReadUpdateDTO.fromEntity(
                projectRepository.findById(id).orElseThrow()
        );
    }
}

