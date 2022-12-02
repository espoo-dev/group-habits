package com.example.habits

import com.example.habits.data.repository.LoginRepository
import com.example.habits.data.repository.LoginRepositoryImpl
import com.example.habits.data.services.UserService
import com.example.habits.domain.LoginUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun configureDataModuleForTest(baseUrl: String) = module {
    single<UserService> {

        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        createServiceForTest(factory = factory, baseUrl = baseUrl)
    }

    single<LoginRepository> { LoginRepositoryImpl(get()) }
}

fun configureDomainModuleForTest() = module {
    factory<LoginUseCase> { LoginUseCase(get()) }
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
