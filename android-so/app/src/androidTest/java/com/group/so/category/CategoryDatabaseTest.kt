package com.group.so.category

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.group.so.DbTest
import com.group.so.data.dao.CategoryDao
import com.group.so.data.database.ServiceOrderDatabase
import com.group.so.data.entities.db.CategoryDb
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
class CategoryDatabaseTest : DbTest() {

    private lateinit var dao: CategoryDao
    private lateinit var postDatabase: ServiceOrderDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        postDatabase = Room.inMemoryDatabaseBuilder(
            context, ServiceOrderDatabase::class.java
        ).build()
        dao = postDatabase.dao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        postDatabase.close()
    }

    @Test
    fun `Should_Record_Category_In_Database_After`() {

        lateinit var result: List<CategoryDb>

        runBlocking {
            result = dao.listCategories().first()
        }
        assertTrue(result.isEmpty())
        runBlocking {
            dao.saveAll(dbCategories)
            result = dao.listCategories().first()
        }
        assertFalse(result.isEmpty())
    }

    @Test
    fun `Should_ReturnCategoriesCorrectly_WhenReadFromDatabase`() {
        lateinit var result: CategoryDb
        runBlocking {
            dao.saveAll(dbCategories)
            result = dao.listCategories().first()[0]
        }
        assertTrue(result.name == dbCategories[0].name)
    }

    @Test
    fun `ShouldClearDatabase_WhenInvokingClearDb`() {
        lateinit var result: List<CategoryDb>
        runBlocking {
            dao.saveAll(dbCategories)
            dao.clearDb()
            result = dao.listCategories().first()
        }
        assertTrue(result.isEmpty())
    }
}
