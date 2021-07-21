package com.everis.domain.repository

import com.everis.domain.entities.User

interface LoginRepositoryPreference {
    fun saveUser(user: User)
    fun saveIsLogin(flag: Boolean)
    fun getUser(): User
    fun isLogin(): Boolean
}