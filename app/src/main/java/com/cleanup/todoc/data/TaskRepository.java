package com.cleanup.todoc.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.data.dao.TaskDao;
import com.cleanup.todoc.data.entity.Task;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;
    private int tasksCount;

    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
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
