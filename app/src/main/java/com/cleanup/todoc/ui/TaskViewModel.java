package com.cleanup.todoc.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.data.TaskRepository;
import com.cleanup.todoc.data.entity.Project;
import com.cleanup.todoc.data.entity.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private final TaskRepository repository;
    private final LiveData<List<Task>> allTasks;
    private final LiveData<List<Project>> allProjects;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
        allProjects = repository.getAllProjects();
    }

    LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    LiveData<List<Project>> getAllProjects(){
        return allProjects;
    }

    public void insert(Task task){
        repository.insert(task);
    }

    public void delete(Task task){
        repository.delete(task);
    }

}
