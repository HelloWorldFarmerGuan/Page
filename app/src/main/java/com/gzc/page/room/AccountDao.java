package com.gzc.page.room;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * User: Administrator
 * Date: 2020-11-15 21:02
 * Describe:
 */
@Dao
public interface AccountDao {
    @Query("select * from account")
    DataSource.Factory<Integer, Account> getAll();

    @Insert
    void insert(Account account);

}
