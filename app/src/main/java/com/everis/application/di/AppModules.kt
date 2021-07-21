package com.everis.application.di

import com.everis.application.screens.login.LoginViewModel
import com.everis.application.utils.DispatcherProvider
import com.everis.application.utils.KeyManagerUtil
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import javax.net.ssl.KeyManager

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

val viewModelsModule = module {
    //single { KeyManagerUtil(get()) }
    //single { providerConnectionRepository(get()) }
    single { DispatcherProvider() }
    viewModel { LoginViewModel(get(), get()) }
}

fun providerConnectionRepository(keyManagerUtil: KeyManagerUtil): Array<KeyManager>? {
    return keyManagerUtil.getKeyManager()
}