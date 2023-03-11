package com.group.so.salesUnit

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.group.so.DbTest
import com.group.so.data.dao.SalesUnitDao
import com.group.so.data.database.ServiceOrderDatabase
import com.group.so.data.entities.db.SalesUnitDb
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
class SalesUnitDatabaseTest : DbTest() {

    private lateinit var dao: SalesUnitDao
    private lateinit var postDatabase: ServiceOrderDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        postDatabase = Room.inMemoryDatabaseBuilder(
            context, ServiceOrderDatabase::class.java
        ).build()
        dao = postDatabase.daoSalesUnit
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        postDatabase.close()
    }

    @Test
    fun `Should_Record_Sales_Unit_In_Database_After`() {

        lateinit var result: List<SalesUnitDb>

        runBlocking {
            result = dao.listSalesUnit().first()
        }
        assertTrue(result.isEmpty())
        runBlocking {
            dao.saveAll(dbSalesUnit)
            result = dao.listSalesUnit().first()
        }
        assertFalse(result.isEmpty())
    }

    @Test
    fun `Should_ReturnSalesUnitCorrectly_WhenReadFromDatabase`() {
        lateinit var result: SalesUnitDb
        runBlocking {
            dao.saveAll(dbSalesUnit)
            result = dao.listSalesUnit().first()[0]
        }
        assertTrue(result.name == dbCategories[0].name)
    }

    @Test
    fun `ShouldClearDatabase_WhenInvokingClearDb`() {
        lateinit var result: List<SalesUnitDb>
        runBlocking {
            dao.saveAll(dbSalesUnit)
            dao.clearDb()
            result = dao.listSalesUnit().first()
        }
        assertTrue(result.isEmpty())
    }
}
