package com.group.so.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.group.so.data.entities.db.ItemDb
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull

@Dao
interface ItemDao {
    @NotNull
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(list: List<ItemDb>)

    @Query("SELECT * FROM item")
    fun listItens(): Flow<List<ItemDb>>

    @Query("DELETE FROM item")
    suspend fun clearAllItens()
}
