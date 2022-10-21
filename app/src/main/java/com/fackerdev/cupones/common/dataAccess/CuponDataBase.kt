package com.fackerdev.cupones.common.dataAccess

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fackerdev.cupones.common.entitites.CuponEntity

@Database(entities = [CuponEntity::class], version = 1)
abstract class CuponDataBase: RoomDatabase(){
    abstract fun cuponDao(): CuponDao
}