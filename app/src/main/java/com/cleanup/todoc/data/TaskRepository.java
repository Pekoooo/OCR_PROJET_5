package com.cleanup.todoc.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.data.dao.ProjectDao;
import com.cleanup.todoc.data.dao.TaskDao;
import com.cleanup.todoc.data.entity.Project;
import com.cleanup.todoc.data.entity.Task;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    private ProjectDao projectDao;
    private LiveData<List<Task>> allTasks;
    private LiveData<List<Project>> allProjects;
    private int tasksCount;

    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        taskDao = db.taskDao();
        projectDao = db.projectDao();
        allTasks = taskDao.getAllTasks();
        allProjects = projectDao.getAllProjects();


    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<List<Project>> getAllProjects() {
        return this.allProjects;
    }

    public void insert(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.insert(task);
        });
    }

    public void delete(Task task){
        AppDatabase.databaseWriteExecutor.execute(()-> {
            taskDao.delete(task);
        });
    }


}
