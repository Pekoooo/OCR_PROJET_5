package com.cleanup.todoc.database.dao;


import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.data.AppDatabase;
import com.cleanup.todoc.data.entity.Project;
import com.cleanup.todoc.data.entity.Task;
import com.cleanup.todoc.database.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private AppDatabase database;
    private final Project[] projects = Project.getAllProjects();
    private final Task task1 = new Task(projects[0].getId(), "Task_Test 1", new Date().getTime());
    private final Task task2 = new Task(projects[0].getId(), "Task_Test 2", new Date().getTime());
    private final Task task3 = new Task(projects[0].getId(), "Task_Test 3", new Date().getTime());


    @Before
    public void createDb()  {
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        database.projectDao().insertAll(projects);
    }

    @After
    public void closeDb() {
        database.close();
    }



    @Test
    public void insertTask() throws Exception {
        database.taskDao().insert(task1);
        database.taskDao().insert(task2);
        List<Task> tasks = LiveDataTestUtil.getValue(database.taskDao().getAllTasks());
        assertEquals(2, tasks.size());
    }

   @Test
   public void deleteTask() throws Exception {
       List<Task> tasks = LiveDataTestUtil.getValue(database.taskDao().getAllTasks());
       database.taskDao().insert(task3);
       database.taskDao().delete(task3);
       assertEquals(0, tasks.size());
   }
}
