package com.example.habits.presentation.di

import com.example.habits.presentation.ui.viewmodel.LoginViewModel
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
        }
    }
}
