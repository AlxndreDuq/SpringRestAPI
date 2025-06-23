package com.jeeProject.TaskManager.Integration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeeProject.TaskManager.DTOs.UserDTO.UsersCreateDTO;
import com.jeeProject.TaskManager.DTOs.UserDTO.UsersReadUpdateDTO;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        projectRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testCreateUser() throws Exception {
        UsersCreateDTO user = new UsersCreateDTO("Martin", "martin@example.com");

        mockMvc.perform(post("/User/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Martin")))
                .andExpect(jsonPath("$.email", is("martin@example.com")));
    }

    @Test
    void testCreateUserValidationError() throws Exception {
        UsersCreateDTO user = new UsersCreateDTO("", "");

        mockMvc.perform(post("/User/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", not(emptyOrNullString())))
                .andExpect(jsonPath("$.email", not(emptyOrNullString())));
    }

    @Test
    void testGetAllUsers() throws Exception {
        Users u = new Users("Alice", "alice@example.com");
        userRepository.save(u);

        mockMvc.perform(get("/User/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Alice")));
    }

    @Test
    void testGetUserById() throws Exception {
        Users user = new Users("Paul", "paul@example.com");
        user = userRepository.save(user);

        mockMvc.perform(get("/User/" + user.getId_user()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Paul")));
    }

    @Test
    void testUpdateUser() throws Exception {
        Users user = new Users("Marie", "marie@example.com");
        user = userRepository.save(user);

        UsersReadUpdateDTO updatedUser = new UsersReadUpdateDTO(
                user.getId_user(),
                "Rebecca",
                "marie.durand@example.com"
        );

        mockMvc.perform(put("/User/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Rebecca")));
    }

    @Test
    void testUpdateUserValidationError() throws Exception {
        UsersReadUpdateDTO updatedUser = new UsersReadUpdateDTO(1L, "", "");

        mockMvc.perform(put("/User/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", not(emptyOrNullString())))
                .andExpect(jsonPath("$.email", not(emptyOrNullString())));
    }

    @Test
    void testDeleteUser() throws Exception {
        Users user = new Users("Lucas", "lucas@example.com");
        user = userRepository.save(user);

        mockMvc.perform(delete("/User/delete/" + user.getId_user()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mockMvc.perform(get("/User/" + user.getId_user()))
                .andExpect(status().is5xxServerError()); // lève une exception
    }
}