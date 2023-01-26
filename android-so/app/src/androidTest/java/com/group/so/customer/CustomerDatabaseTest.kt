package com.group.so.customer

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.group.so.data.dao.CustomerDao
import com.group.so.data.database.ServiceOrderDatabase
import com.group.so.data.entities.db.CustomerDb
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
class CustomerDatabaseTest : FakeCustomerTest() {

    private lateinit var dao: CustomerDao
    private lateinit var serviceOrderDatabase: ServiceOrderDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        serviceOrderDatabase = Room.inMemoryDatabaseBuilder(
            context, ServiceOrderDatabase::class.java
        ).build()
        dao = serviceOrderDatabase.daoCustomer
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        serviceOrderDatabase.close()
    }

    @Test
    fun `Should_Record_Customer_In_Database_After`() {

        lateinit var result: List<CustomerDb>

        runBlocking {
            result = dao.listCustomers().first()
        }
        assertTrue(result.isEmpty())
        runBlocking {
            dao.saveAll(dbCustomersDb)
            result = dao.listCustomers().first()
        }
        assertFalse(result.isEmpty())
    }

    @Test
    fun `Should_ReturnCustomersCorrectly_WhenReadFromDatabase`() {
        lateinit var result: CustomerDb
        runBlocking {
            dao.saveAll(dbCustomersDb)
            result = dao.listCustomers().first()[0]
        }
        assertTrue(result.name == dbCustomersDb[0].name)
    }

    @Test
    fun `ShouldClearDatabase_WhenInvokingClearDb`() {
        lateinit var result: List<CustomerDb>
        runBlocking {
            dao.saveAll(dbCustomersDb)
            dao.clearAllCustomer()
            result = dao.listCustomers().first()
        }
        assertTrue(result.isEmpty())
    }
}
