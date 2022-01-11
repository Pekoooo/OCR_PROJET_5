package com.cleanup.todoc.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.data.entity.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    @Insert
    void insert(Project project);

    @Query("DELETE FROM project_table")
    void deleteAll();

    // @Query("SELECT * FROM project_table")
    // LiveData<List<Project>> getAll();

}
