package com.jeeProject.TaskManager.Unitaire;

import com.jeeProject.TaskManager.DTOs.UserDTO.UsersCreateDTO;
import com.jeeProject.TaskManager.DTOs.UserDTO.UsersReadUpdateDTO;
import com.jeeProject.TaskManager.Entity.Users;
import com.jeeProject.TaskManager.Repository.UserRepository;
import com.jeeProject.TaskManager.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Users user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new Users(1L, "John", "john@example.com");
    }

    @Test
    void testCreateUser() {
        UsersCreateDTO dto = new UsersCreateDTO("John", "john@example.com");
        when(userRepository.save(any())).thenReturn(user);

        UsersReadUpdateDTO result = userService.createUser(dto);

        assertEquals("John", result.getName());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void testUpdateUser() {
        user.setName("Doe");

        UsersReadUpdateDTO dto = UsersReadUpdateDTO.fromEntity(user);
        when(userRepository.save(any())).thenReturn(user);

        UsersReadUpdateDTO result = userService.updateUser(dto);

        assertEquals("Doe", result.getName());
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UsersReadUpdateDTO result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId_user());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        var result = userService.getUsers();

        assertEquals(1, result.size());
    }
}