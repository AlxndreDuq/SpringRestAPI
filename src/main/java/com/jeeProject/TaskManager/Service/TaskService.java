package com.jeeProject.TaskManager.Service;

import com.jeeProject.TaskManager.DTOs.TaskDTO.TaskCreateDTO;
import com.jeeProject.TaskManager.DTOs.TaskDTO.TaskReadUpdateDTO;
import com.jeeProject.TaskManager.Entity.Task;
import com.jeeProject.TaskManager.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskReadUpdateDTO createTask(TaskCreateDTO dto) {
        Task saved = taskRepository.save(dto.toEntity());
        return TaskReadUpdateDTO.fromEntity(saved);
    }

    public TaskReadUpdateDTO updateTask(TaskReadUpdateDTO dto) {
        Task updated = taskRepository.save(dto.toEntity());
        return TaskReadUpdateDTO.fromEntity(updated);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<TaskReadUpdateDTO> getAllTasks() {
        return TaskReadUpdateDTO.fromEntityList(taskRepository.findAll());
    }

    public TaskReadUpdateDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        return TaskReadUpdateDTO.fromEntity(task);
    }

    public TaskReadUpdateDTO nextStatus(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.getStatus().next(task);
        return TaskReadUpdateDTO.fromEntity(taskRepository.save(task));
    }

    public TaskReadUpdateDTO previousStatus(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.getStatus().previous(task);
        return TaskReadUpdateDTO.fromEntity(taskRepository.save(task));
    }

}
