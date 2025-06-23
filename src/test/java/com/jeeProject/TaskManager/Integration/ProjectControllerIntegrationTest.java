package com.jeeProject.TaskManager.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeeProject.TaskManager.DTOs.ProjectDTO.ProjectCreateDTO;
import com.jeeProject.TaskManager.DTOs.ProjectDTO.ProjectReadUpdateDTO;
import com.jeeProject.TaskManager.Entity.Project;
import com.jeeProject.TaskManager.Entity.Users;
import com.jeeProject.TaskManager.Repository.ProjectRepository;
import com.jeeProject.TaskManager.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    private Users user;

    @BeforeEach
    void setUp() {
        projectRepository.deleteAll();
        userRepository.deleteAll();
        user = userRepository.save(new Users("Test User", "test@project.com"));
    }

    @Test
    void testCreateProject() throws Exception {
        ProjectCreateDTO project = new ProjectCreateDTO("Projet A", "Description A", user);

        mockMvc.perform(post("/Project/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Projet A")))
                .andExpect(jsonPath("$.description", is("Description A")))
                .andExpect(jsonPath("$.owner.name", is("Test User")));
    }

    @Test
    void testCreateProjectValidationError() throws Exception {
        ProjectCreateDTO project = new ProjectCreateDTO("", "", null);

        mockMvc.perform(post("/Project/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.owner", notNullValue()));
    }

    @Test
    void testGetAllProjects() throws Exception {
        projectRepository.save(new Project("P1", "Desc", new Date(), user, null));

        mockMvc.perform(get("/Project/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("P1")));
    }

    @Test
    void testGetProjectById() throws Exception {
        Project project = projectRepository.save(new Project("P2", "Desc", new Date(), user, null));

        mockMvc.perform(get("/Project/" + project.getId_project()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("P2")));
    }

    @Test
    void testUpdateProject() throws Exception {
        Project project = projectRepository.save(new Project("Old", "Old desc", new Date(), user, null));
        ProjectReadUpdateDTO updated = new ProjectReadUpdateDTO(project.getId_project(), "New", "New desc", project.getCreatedAt(), user, null);

        mockMvc.perform(put("/Project/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("New")));
    }

    @Test
    void testUpdateProjectValidationError() throws Exception {
        Project project = projectRepository.save(new Project("ToValidate", "Valid desc", new Date(), user, null));
        ProjectReadUpdateDTO invalidUpdate = new ProjectReadUpdateDTO(project.getId_project(), "", "", project.getCreatedAt(), null, null);

        mockMvc.perform(put("/Project/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUpdate)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.owner", notNullValue()));
    }

    @Test
    void testDeleteProject() throws Exception {
        Project project = projectRepository.save(new Project("ToDelete", "Desc", new Date(), user, null));

        mockMvc.perform(delete("/Project/delete/" + project.getId_project()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mockMvc.perform(get("/Project/" + project.getId_project()))
                .andExpect(status().is5xxServerError());
    }
}