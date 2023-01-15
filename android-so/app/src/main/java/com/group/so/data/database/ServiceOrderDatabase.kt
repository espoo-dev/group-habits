package com.group.so.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.group.so.data.dao.CategoryDao
import com.group.so.data.entities.db.CategoryDb

@Database(
    entities = [CategoryDb::class],
    version = 1,
    exportSchema = false
)
abstract class ServiceOrderDatabase : RoomDatabase() {
    abstract val dao: CategoryDao

    companion object {

        @Volatile
        private var INSTANCE: ServiceOrderDatabase? = null

        fun getInstance(context: Context): ServiceOrderDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ServiceOrderDatabase::class.java,
                        "service_order_cache_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
