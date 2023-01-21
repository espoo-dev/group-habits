package com.group.so

import com.group.so.data.database.ServiceOrderDatabase
import com.group.so.data.repository.LoginRepository
import com.group.so.data.repository.LoginRepositoryImpl
import com.group.so.data.repository.category.CategoryRepository
import com.group.so.data.repository.category.CategoryRepositoryImpl
import com.group.so.data.services.CategoryService
import com.group.so.data.services.SessionManager
import com.group.so.data.services.UserService
import com.group.so.domain.LoginUseCase
import com.group.so.domain.category.*
import com.group.so.presentation.ui.category.CategoryViewModel
import com.group.so.presentation.ui.login.LoginViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun configureDataModuleForTest(baseUrl: String) = module {
    single<UserService> {
        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        createServiceForTest(factory = factory, baseUrl = baseUrl)
    }
    single<CategoryService> {
        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        createServiceForTest(factory = factory, baseUrl = baseUrl)
    }

    single {
        SessionManager(androidContext())
    }

    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get(), get()) }
}

fun configureDomainModuleForTest() = module {
    factory<LoginUseCase> { LoginUseCase(get()) }

    factory<GetCategoriesUseCase> { GetCategoriesUseCase(get()) }
    factory<RegisterCategoryUseCase> { RegisterCategoryUseCase(get()) }
    factory<DeleteCategoryUseCase> { DeleteCategoryUseCase(get()) }
    factory<EditCategoryUseCase> { EditCategoryUseCase(get()) }
    factory<SearchCategoriesUseCase> { SearchCategoriesUseCase(get()) }
}

fun configureDAOModuleForTest() = module {
    single { ServiceOrderDatabase.getInstance(androidContext()).dao }
}

fun configurePresentationModuleForTest() = module {
    factory { LoginViewModel(get()) }
    factory { CategoryViewModel(get(), get(), get(), get(),get()) }
}

private inline fun <reified T> createServiceForTest(
    factory: Moshi,
    baseUrl: String
): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(factory))
        .build()
        .create(T::class.java)
}
