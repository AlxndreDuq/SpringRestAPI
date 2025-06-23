package com.jeeProject.TaskManager.Unitaire;

import com.jeeProject.TaskManager.DTOs.TaskDTO.TaskCreateDTO;
import com.jeeProject.TaskManager.DTOs.TaskDTO.TaskReadUpdateDTO;
import com.jeeProject.TaskManager.Entity.Project;
import com.jeeProject.TaskManager.Entity.Task;
import com.jeeProject.TaskManager.Entity.TaskStatus;
import com.jeeProject.TaskManager.Entity.Users;
import com.jeeProject.TaskManager.Service.TaskService;
import com.jeeProject.TaskManager.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private Users user;
    private Project project;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new Users();
        project = new Project();
        task = new Task(1L, "Tâche A", "Description A", TaskStatus.TODO, user, project);
    }

    @Test
    void testCreateTask() {
        TaskCreateDTO dto = new TaskCreateDTO("Tâche A", "Description A", TaskStatus.TODO, user, project);
        when(taskRepository.save(any())).thenReturn(task);

        TaskReadUpdateDTO result = taskService.createTask(dto);

        assertEquals("Tâche A", result.getTitle());
        verify(taskRepository, times(1)).save(any());
    }

    @Test
    void testUpdateTask() {
        task.setDescription("Description B");

        TaskReadUpdateDTO dto = TaskReadUpdateDTO.fromEntity(task);
        when(taskRepository.save(any())).thenReturn(task);

        TaskReadUpdateDTO result = taskService.updateTask(dto);

        assertEquals("Description B", result.getDescription());
    }

    @Test
    void testGetTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskReadUpdateDTO result = taskService.getTaskById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testDeleteTask() {
        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllTasks() {
        when(taskRepository.findAll()).thenReturn(Collections.singletonList(task));

        var result = taskService.getAllTasks();

        assertEquals(1, result.size());
    }
}