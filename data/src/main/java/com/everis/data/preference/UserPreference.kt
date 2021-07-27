package com.everis.data.preference

import com.everis.data.preference.manager.PreferencesManager
import com.everis.domain.entities.User
import com.everis.domain.repository.LoginRepositoryPreference

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

class UserPreference(private val sharedPreferenceManager: PreferencesManager) :
    LoginRepositoryPreference {

    override fun saveUser(user: User) {
        sharedPreferenceManager.setValue("USERNAME", user.userName!!)
    }


    override fun saveIsLogin(flag: Boolean) {
        sharedPreferenceManager.setValue("ISLOGIN", flag)
    }



    override fun getUser(): User {
        val userName = sharedPreferenceManager.getString("USERNAME")
        return User(userName)
    }

    override fun isLogin(): Boolean {
        return sharedPreferenceManager.getBoolean("ISLOGIN")
    }



}