package com.everis.domain.di

import com.everis.domain.usecases.LoginUseCase
import org.koin.dsl.module

val domainModule = module {
    single { LoginUseCase(get(), get()) }
}