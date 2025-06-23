package com.jeeProject.TaskManager.Entity;

public enum TaskStatus {
    TODO("TODO", new TodoAction()),
    IN_PROGRESS("IN_PROGRESS", new InProgressAction()),
    DONE("DONE", new DoneAction());

    private final String label;
    private final IActionStatus action;

    TaskStatus(String label, IActionStatus action) {
        this.label = label;
        this.action = action;
    }

    public void next(Task t){
        action.next(t);
    }

    public void previous(Task t){
        action.previous(t);
    }
}
