package com.group.so

import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

const val BASE_URL = "localhost:3000/api/v1/"

fun configureTestAppComponent() = startKoin {
    loadKoinModules(
        configureDataModuleForTest(BASE_URL) + configureDomainModuleForTest() +
            configurePresentationModuleForTest() + configureDAOModuleForTest()
    )
}
