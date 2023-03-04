package com.group.so.presentation.di

import com.group.so.core.ui.components.SharedViewModel
import com.group.so.presentation.ui.category.CategoryViewModel
import com.group.so.presentation.ui.customer.CustomerViewModel
import com.group.so.presentation.ui.login.LoginViewModel
import com.group.so.presentation.ui.product.ProductViewModel
import com.group.so.presentation.ui.service.ServiceViewModel
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {
        return module {
            factory { LoginViewModel(get()) }
            factory { CategoryViewModel(get(), get(), get(), get(), get()) }
            factory { CustomerViewModel(get(), get(), get(), get(), get(), get()) }
            factory { ServiceViewModel(get(), get(), get(), get(), get()) }
            factory { ProductViewModel(get(), get(), get(), get(), get(), get()) }
            factory { SharedViewModel() }
        }
    }
}
