package com.fackerdev.cupones

import android.app.Application
import androidx.room.Room
import com.fackerdev.cupones.common.dataAccess.CuponDataBase

class CuponApplication: Application() {
    companion object {
        lateinit var database: CuponDataBase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            CuponDataBase::class.java,
            "CuponDataBase")
            .build()
    }
}