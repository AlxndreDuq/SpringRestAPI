package com.jeeProject.TaskManager.Entity;

public class TodoAction implements IActionStatus {
    @Override
    public void next(Task t) {
        t.setStatus(TaskStatus.IN_PROGRESS);
    }
    @Override
    public void previous(Task t) {
        throw new IllegalStateException("Il n'existe pas d'état qui précède TODO");
    }

}
