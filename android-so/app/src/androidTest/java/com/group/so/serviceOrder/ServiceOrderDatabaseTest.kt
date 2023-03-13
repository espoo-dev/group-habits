package com.group.so.serviceOrder

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.group.so.data.dao.ServiceOrderDao
import com.group.so.data.database.ServiceOrderDatabase
import com.group.so.data.entities.db.ServiceOrderDb
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ServiceOrderDatabaseTest : FakeItemTest() {

    private lateinit var dao: ServiceOrderDao
    private lateinit var serviceOrderDatabase: ServiceOrderDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        serviceOrderDatabase = Room.inMemoryDatabaseBuilder(
            context, ServiceOrderDatabase::class.java
        ).build()
        dao = serviceOrderDatabase.daoServiceOrder
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        serviceOrderDatabase.close()
    }

    @Test
    fun `Should_Record_ServiceOrder_In_Database_After`() {

        lateinit var result: List<ServiceOrderDb>

        runBlocking {
            result = dao.listServiceOrders().first()
        }
        assertTrue(result.isEmpty())
        runBlocking {
            dao.saveAll(dbServiceOrderDb)
            result = dao.listServiceOrders().first()
        }
        assertFalse(result.isEmpty())
    }

    @Test
    fun `Should_ReturnsServiceOrderCorrectly_WhenReadFromDatabase`() {
        lateinit var result: ServiceOrderDb
        runBlocking {
            dao.saveAll(dbServiceOrderDb)
            result = dao.listServiceOrders().first()[0]
        }
        assertTrue(result.id == dbServiceOrderDb[0].id)
    }

    @Test
    fun `ShouldClearDatabase_WhenInvokingClearDb`() {
        lateinit var result: List<ServiceOrderDb>
        runBlocking {
            dao.saveAll(dbServiceOrderDb)
            dao.clearDb()
            result = dao.listServiceOrders().first()
        }
        assertTrue(result.isEmpty())
    }
}
