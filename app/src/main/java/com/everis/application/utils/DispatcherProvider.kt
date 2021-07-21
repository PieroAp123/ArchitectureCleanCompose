package com.everis.application.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by WilderCs on 2019-11-12.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

open class DispatcherProvider {
    open val IO: CoroutineDispatcher = Dispatchers.IO
    open val Main: CoroutineDispatcher = Dispatchers.Main
    open val Unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}