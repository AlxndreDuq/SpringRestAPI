package com.jeeProject.TaskManager.Unitaire;

import com.jeeProject.TaskManager.DTOs.ProjectDTO.ProjectCreateDTO;
import com.jeeProject.TaskManager.DTOs.ProjectDTO.ProjectReadUpdateDTO;
import com.jeeProject.TaskManager.Entity.Project;
import com.jeeProject.TaskManager.Entity.Users;
import com.jeeProject.TaskManager.Repository.ProjectRepository;
import com.jeeProject.TaskManager.Service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;
    private Users owner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        owner = new Users(1L, "a", "b@b.fr"); // instancie un utilisateur fictif
        project = new Project(1L, "Test Project", "Description", new Date(), owner, Collections.emptyList());
    }

    @Test
    void testCreateProject() {
        ProjectCreateDTO dto = new ProjectCreateDTO("Test Project", "Description", owner);
        when(projectRepository.save(any())).thenReturn(project);

        ProjectReadUpdateDTO result = projectService.createProject(dto);

        assertEquals("Test Project", result.getName());
        verify(projectRepository, times(1)).save(any());
    }

    @Test
    void testUpdateProject() {
        project.setName("Other name");
        ProjectReadUpdateDTO dto = ProjectReadUpdateDTO.fromEntity(project);
        when(projectRepository.save(any())).thenReturn(project);

        ProjectReadUpdateDTO result = projectService.updateProject(dto);

        assertEquals("Other name", result.getName());
    }

    @Test
    void testGetProjectById() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjectReadUpdateDTO result = projectService.getProjectById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testDeleteProject() {
        projectService.deleteProject(1L);
        verify(projectRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllProjects() {
        when(projectRepository.findAll()).thenReturn(Collections.singletonList(project));

        var result = projectService.getAllProjects();

        assertEquals(1, result.size());
        verify(projectRepository, times(1)).findAll();
    }
}

