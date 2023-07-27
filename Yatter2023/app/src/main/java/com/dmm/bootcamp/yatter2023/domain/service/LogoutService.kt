package com.dmm.bootcamp.yatter2023.domain.service

import android.content.Context
import com.dmm.bootcamp.yatter2023.infra.pref.MePreferences

interface LogoutService{
    suspend fun execute(){
    }
}