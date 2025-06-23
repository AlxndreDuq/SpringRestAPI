package com.jeeProject.TaskManager.Entity;

public class DoneAction implements IActionStatus{
    @Override
    public void next(Task t) {
        throw new IllegalStateException("Done est le dernier statut");
    }

    @Override
    public void previous(Task t) {
        t.setStatus(TaskStatus.IN_PROGRESS);
    }
}
