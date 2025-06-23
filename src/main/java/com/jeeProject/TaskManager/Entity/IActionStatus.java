package com.jeeProject.TaskManager.Entity;

interface IActionStatus {

    void next(Task t);
    void previous(Task t);
}
