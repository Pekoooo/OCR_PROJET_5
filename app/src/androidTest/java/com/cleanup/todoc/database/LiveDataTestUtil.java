package com.cleanup.todoc.database;

import androidx.annotation.Nullable;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;


import com.cleanup.todoc.data.AppDatabase;
import com.cleanup.todoc.data.dao.TaskDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class LiveDataTestUtil {
    public static <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        //noinspection unchecked
        return (T) data[0];
    }
}
