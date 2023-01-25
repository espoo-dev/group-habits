package com.group.so.domain.category

import com.group.so.configureTestAppComponent
import com.group.so.core.Resource
import com.group.so.data.repository.category.CategoryRepository
import com.group.so.mock.CategoryMock
import io.mockk.coEvery
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(JUnit4::class)
class Test : KoinTest {

    val categoryRepository: CategoryRepository by inject()
    val getCategoriesUseCase: GetCategoriesUseCase by inject()

    companion object {

        @BeforeClass
        @JvmStatic
        fun setup() {
            configureTestAppComponent()
        }

        /**
         * Stop Koin after each test to prevent errors
         */
        @AfterClass
        fun tearDown() {
            stopKoin()
        }
    }

    @Test
    fun `getUsers com repository ok deve retornar sucesso`() = runBlocking {

        coEvery { categoryRepository.listCategories() } returns CategoryMock.mockCategoryResourceSuccessFlow()

        println("LIST" + categoryRepository.listCategories().first())

        val result = getCategoriesUseCase.execute().first()

        // THEN
        Assert.assertEquals(
            result.data?.get(0)?.id,
            CategoryMock.mockCategoryResourceSuccessFlow().first().data?.get(0)?.id
        )
        Assert.assertEquals(
            result.data, CategoryMock.mockCategoryResourceSuccessFlow().first().data
        )
        Assert.assertTrue(result is Resource.Success)
    }
}
