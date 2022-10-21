package com.fackerdev.cupones.common.dataAccess

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fackerdev.cupones.common.entitites.CuponEntity

@Dao
interface CuponDao {

    @Query("SELECT * FROM CuponEntity WHERE code=:code")
    suspend fun consultCuponByCode(code:String): CuponEntity?

    @Insert
    suspend fun addCupon(cuponEntity: CuponEntity):Long
}