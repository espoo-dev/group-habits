package com.group.so.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.group.so.data.entities.db.CategoryDb
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull

@Dao
interface CategoryDao {

    @NotNull
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(list: List<CategoryDb>)

    @Query("SELECT * FROM category")
    fun listCategories(): Flow<List<CategoryDb>>

    @Query("DELETE FROM category")
    suspend fun clearDb()
}
