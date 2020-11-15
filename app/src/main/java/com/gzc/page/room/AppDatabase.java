package com.gzc.page.room;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

/**
 * User: Administrator
 * Date: 2020-11-15 20:58
 * Describe:
 */
@Database(entities = {Account.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountDao getAccountDao();
}
