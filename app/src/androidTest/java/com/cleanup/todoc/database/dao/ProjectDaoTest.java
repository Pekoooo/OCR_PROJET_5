package com.cleanup.todoc.database.dao;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.data.AppDatabase;
import com.cleanup.todoc.data.entity.Project;
import com.cleanup.todoc.database.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private AppDatabase database;
    private final Project[] projects = Project.getAllProjects();

    @Before
    public void createDb()  {
        Context context = ApplicationProvider.getApplicationContext();
        this.database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();

    }

    @After
    public void closeDatabase() {
        this.database.close();
    }

      @Test
      public void insertAndGetProject() throws InterruptedException {
          List<Project> projects;

          this.database.projectDao().insertAll(this.projects);

          projects = LiveDataTestUtil.getValue(database.projectDao().getAllProjects());
          assertEquals(projects.get(0).getName(), this.projects[0].getName());
          assertEquals(projects.get(0).getId(), this.projects[0].getId());
          assertEquals(projects.get(0).getColor(), this.projects[0].getColor());

          assertEquals(projects.get(1).getName(), this.projects[1].getName());
          assertEquals(projects.get(1).getId(), this.projects[1].getId());
          assertEquals(projects.get(1).getColor(), this.projects[1].getColor());

          assertEquals(projects.get(2).getName(), this.projects[2].getName());
          assertEquals(projects.get(2).getId(), this.projects[2].getId());
          assertEquals(projects.get(2).getColor(), this.projects[2].getColor());
      }
}
