package com.fackerdev.cupones.modules.mainModule.model

import android.database.sqlite.SQLiteConstraintException
import com.fackerdev.cupones.CuponApplication
import com.fackerdev.cupones.common.dataAccess.CuponDao
import com.fackerdev.cupones.common.entitites.CuponEntity
import com.fackerdev.cupones.common.utils.Constans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDatabase {
    private val cuponDao: CuponDao by lazy{ CuponApplication.database.cuponDao() }

    suspend fun consultCuponByCode(code:String) =cuponDao.consultCuponByCode(code)

    suspend fun addCupon(cuponEntity: CuponEntity) = withContext(Dispatchers.IO){
        try{
            cuponDao.addCupon(cuponEntity)
        }catch (e:Exception){
            (e as? SQLiteConstraintException).let { throw Exception(Constans.ERROR_EXISTS) }
            throw Exception(e.message ?: Constans.ERROR_UNKNOW)
        }
    }
}