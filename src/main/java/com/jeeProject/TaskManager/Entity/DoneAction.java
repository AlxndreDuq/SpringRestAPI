package com.jeeProject.TaskManager.Entity;

public class DoneAction implements IActionStatus{
    @Override
    public void next(Task t) {
        throw new IllegalStateException("Il n'existe pas d'état après DONE");
    }

    @Override
    public void previous(Task t) {
        t.setStatus(TaskStatus.IN_PROGRESS);
    }
}
