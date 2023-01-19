package com.group.so.domain.di

import com.group.so.domain.LoginUseCase
import com.group.so.domain.category.DeleteCategoryUseCase
import com.group.so.domain.category.GetCategoriesUseCase
import com.group.so.domain.category.RegisterCategoryUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { LoginUseCase(get()) }

            factory { GetCategoriesUseCase(get()) }
            factory { RegisterCategoryUseCase(get()) }
            factory { DeleteCategoryUseCase(get()) }
        }
    }
}
