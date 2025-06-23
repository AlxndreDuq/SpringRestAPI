package com.jeeProject.TaskManager.DTOs;

public abstract class BaseDTO <T>{
    /**
     * Convertit ce DTO vers son entité correspondante.
     */
    public abstract T toEntity();

    /**
     * Convertit une entité en DTO. À redéfinir dans les classes filles.
     */
    public static <D, T> D fromEntity(T entity) {
        throw new UnsupportedOperationException("fromEntity doit être implémentée dans la sous-classe.");
    }
}
