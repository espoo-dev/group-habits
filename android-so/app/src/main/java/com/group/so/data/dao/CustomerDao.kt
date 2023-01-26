package com.group.so.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.group.so.data.entities.db.CustomerDb
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull

@Dao
interface CustomerDao {

    @NotNull
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(list: List<CustomerDb>)

    @Query("SELECT * FROM customer")
    fun listCustomers(): Flow<List<CustomerDb>>

    @Query("DELETE FROM customer")
    suspend fun clearAllCustomer()
}
