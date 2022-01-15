package com.cleanup.todoc;


import static androidx.test.espresso.matcher.ViewMatchers.assertThat;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.data.AppDatabase;
import com.cleanup.todoc.data.dao.TaskDao;
import com.cleanup.todoc.data.entity.Task;
import com.cleanup.todoc.utils.LiveDataTestUtil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

@RunWith(AndroidJUnit4.class)
public class DaoTest {
    private TaskDao taskDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        taskDao = db.taskDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void InsertUser() throws Exception {
        Task task = new Task(2, "Test task", 10);
        taskDao.insert(task);
        List<Task> tasks = LiveDataTestUtil.getValue(taskDao.getAllTasks());
        assertEquals(1, tasks.size());
        assertEquals(task.getName(), tasks.get(0).getName());
    }

    @Test
    public void DeleteUser() throws Exception {
        Task taskToDelete = new Task(2, "Test task", 10);
        List<Task> tasks = LiveDataTestUtil.getValue(taskDao.getAllTasks());
        taskDao.insert(taskToDelete);
        taskDao.delete(taskToDelete);
        assertEquals(0, tasks.size());
    }


}
