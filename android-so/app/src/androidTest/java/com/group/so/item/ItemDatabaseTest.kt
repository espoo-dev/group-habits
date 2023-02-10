package com.group.so.item

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.group.so.data.dao.ItemDao
import com.group.so.data.database.ServiceOrderDatabase
import com.group.so.data.entities.db.ItemDb
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
class ItemDatabaseTest : FakeItemTest() {

    private lateinit var dao: ItemDao
    private lateinit var serviceOrderDatabase: ServiceOrderDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        serviceOrderDatabase = Room.inMemoryDatabaseBuilder(
            context, ServiceOrderDatabase::class.java
        ).build()
        dao = serviceOrderDatabase.daoItem
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        serviceOrderDatabase.close()
    }

    @Test
    fun `Should_Record_Item_In_Database_After`() {

        lateinit var result: List<ItemDb>

        runBlocking {
            result = dao.listItens().first()
        }
        assertTrue(result.isEmpty())
        runBlocking {
            dao.saveAll(dbItensDb)
            result = dao.listItens().first()
        }
        assertFalse(result.isEmpty())
    }

    @Test
    fun `Should_ReturnItensCorrectly_WhenReadFromDatabase`() {
        lateinit var result: ItemDb
        runBlocking {
            dao.saveAll(dbItensDb)
            result = dao.listItens().first()[0]
        }
        assertTrue(result.name == dbItensDb[0].name)
    }

    @Test
    fun `ShouldClearDatabase_WhenInvokingClearDb`() {
        lateinit var result: List<ItemDb>
        runBlocking {
            dao.saveAll(dbItensDb)
            dao.clearAllItens()
            result = dao.listItens().first()
        }
        assertTrue(result.isEmpty())
    }
}
