package com.jeeProject.TaskManager.Service;

import com.jeeProject.TaskManager.DTOs.UserDTO.UsersCreateDTO;
import com.jeeProject.TaskManager.DTOs.UserDTO.UsersReadUpdateDTO;
import com.jeeProject.TaskManager.Entity.Users;
import com.jeeProject.TaskManager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UsersReadUpdateDTO createUser(UsersCreateDTO u){
        return UsersReadUpdateDTO.fromEntity(userRepository.save(u.toEntity()));
    }

    public UsersReadUpdateDTO updateUser(UsersReadUpdateDTO u){
        return UsersReadUpdateDTO.fromEntity(userRepository.save(u.toEntity()));
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public List<UsersReadUpdateDTO> getUsers(){
        return UsersReadUpdateDTO.fromEntityList(userRepository.findAll());
    }

    public UsersReadUpdateDTO getUserById(Long userId){
        return UsersReadUpdateDTO.fromEntity(userRepository.findById(userId).orElseThrow());
    }

}
