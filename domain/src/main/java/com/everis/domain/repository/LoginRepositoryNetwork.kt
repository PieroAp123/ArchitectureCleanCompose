package com.everis.domain.repository

import com.everis.domain.entities.CustomResult
import com.everis.domain.entities.User

interface LoginRepositoryNetwork {
    fun doLogin(username: String, password: String): CustomResult<User>
}