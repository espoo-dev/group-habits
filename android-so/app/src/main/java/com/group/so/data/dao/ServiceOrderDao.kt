package com.group.so.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.group.so.data.entities.db.ServiceOrderDb
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull

@Dao
interface ServiceOrderDao {
    @NotNull
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(list: List<ServiceOrderDb>)

    @Query("SELECT * FROM service_order")
    fun listServiceOrders(): Flow<List<ServiceOrderDb>>

    @Query("DELETE FROM service_order")
    suspend fun clearDb()
}
