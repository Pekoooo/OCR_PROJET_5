package com.cleanup.todoc.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.data.entity.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    @Insert
    void insert(Project project);

    @Insert
    void insertAll(Project[] project);

    @Query("SELECT * FROM project_table")
    LiveData<List<Project>> getAllProjects();

    @Query("DELETE FROM project_table")
    void deleteAll();



}
