package com.fackerdev.cupones.modules.mainModule.model

import com.fackerdev.cupones.common.entitites.CuponEntity
import com.fackerdev.cupones.common.utils.Constans
import com.fackerdev.cupones.common.utils.validateTextCode

class MainRepository {

    private val roomDatabase:RoomDatabase = RoomDatabase()


    suspend fun consultCuponByCode(code:String) = roomDatabase.consultCuponByCode(code)

    suspend fun addCupon(cuponEntity: CuponEntity){
        if(validateTextCode(cuponEntity.code))
            roomDatabase.addCupon(cuponEntity)
        else
            throw Exception(Constans.ERROR_LENGTH)
    }
}