package com.dmm.bootcamp.yatter2023.usecase.impl.login

import android.content.Context
import com.dmm.bootcamp.yatter2023.domain.service.LogoutService
import com.dmm.bootcamp.yatter2023.infra.pref.MePreferences

class LogoutServiceImpl(private val context: Context): LogoutService {
    override suspend fun execute() {
        val mePreferences = MePreferences(context)
        mePreferences.clear()
    }
}