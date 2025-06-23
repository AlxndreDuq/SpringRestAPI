package com.jeeProject.TaskManager.Entity;

public class InProgressAction implements IActionStatus {

    @Override
    public void next(Task t) {
        t.setStatus(TaskStatus.DONE);
    }

    @Override
    public void previous(Task t) {
        t.setStatus(TaskStatus.TODO);
    }
}
