package com.group.so.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.group.so.data.entities.db.SalesUnitDb
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull

@Dao
interface SalesUnitDao {
    @NotNull
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(list: List<SalesUnitDb>)

    @Query("SELECT * FROM salesUnit")
    fun listSalesUnit(): Flow<List<SalesUnitDb>>

    @Query("DELETE FROM salesUnit")
    suspend fun clearDb()
}
