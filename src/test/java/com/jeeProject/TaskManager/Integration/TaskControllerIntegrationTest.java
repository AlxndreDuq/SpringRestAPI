package com.jeeProject.TaskManager.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeeProject.TaskManager.DTOs.TaskDTO.TaskCreateDTO;
import com.jeeProject.TaskManager.DTOs.TaskDTO.TaskReadUpdateDTO;
import com.jeeProject.TaskManager.Entity.Project;
import com.jeeProject.TaskManager.Entity.Task;
import com.jeeProject.TaskManager.Entity.TaskStatus;
import com.jeeProject.TaskManager.Entity.Users;
import com.jeeProject.TaskManager.Repository.ProjectRepository;
import com.jeeProject.TaskManager.Repository.TaskRepository;
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
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private Users user;
    private Project project;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
        projectRepository.deleteAll();
        userRepository.deleteAll();

        user = userRepository.save(new Users("TaskUser", "task@user.com"));
        project = projectRepository.save(new Project("TaskProject", "desc", new Date(), user, null));
    }

    @Test
    void testCreateTask() throws Exception {
        TaskCreateDTO dto = new TaskCreateDTO("Task 1", "Desc", TaskStatus.TODO, user, project);

        mockMvc.perform(post("/Task/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Task 1")))
                .andExpect(jsonPath("$.assignedTo.name", is("TaskUser")))
                .andExpect(jsonPath("$.project.name", is("TaskProject")));
    }

    @Test
    void testCreateTaskValidationError() throws Exception {
        TaskCreateDTO dto = new TaskCreateDTO("", "", null, null, null);

        mockMvc.perform(post("/Task/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", notNullValue()));
    }

    @Test
    void testGetAllTasks() throws Exception {
        taskRepository.save(new Task("T1", "D1", TaskStatus.TODO, user, project));

        mockMvc.perform(get("/Task/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("T1")));
    }

    @Test
    void testGetTaskById() throws Exception {
        Task task = taskRepository.save(new Task("T2", "D2", TaskStatus.TODO, user, project));

        mockMvc.perform(get("/Task/" + task.getId_task()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("T2")));
    }

    @Test
    void testUpdateTask() throws Exception {
        Task task = taskRepository.save(new Task("Old T", "Old D", TaskStatus.TODO, user, project));
        TaskReadUpdateDTO updated = new TaskReadUpdateDTO(task.getId_task(), "New T", "New D", TaskStatus.DONE, user, project);

        mockMvc.perform(put("/Task/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("New T")))
                .andExpect(jsonPath("$.status", is("DONE")));
    }

    @Test
    void testUpdateTaskValidationError() throws Exception {
        Task task = taskRepository.save(new Task("Valid", "Desc", TaskStatus.TODO, user, project));
        TaskReadUpdateDTO invalid = new TaskReadUpdateDTO(task.getId_task(), "", "", null, null, null);

        mockMvc.perform(put("/Task/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", notNullValue()));
    }

    @Test
    void testDeleteTask() throws Exception {
        Task task = taskRepository.save(new Task("Delete", "Desc", TaskStatus.TODO, user, project));

        mockMvc.perform(delete("/Task/delete/" + task.getId_task()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mockMvc.perform(get("/Task/" + task.getId_task()))
                .andExpect(status().is5xxServerError());
    }
}
