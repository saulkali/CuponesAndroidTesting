package com.fackerdev.cupones.common.entitites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "CuponEntity", indices = [Index("code", unique = true)])
data class CuponEntity(
    @PrimaryKey(autoGenerate = true) var id:Long = 0,
    var code:String = "",
    var description:String = "",
    var isActive:Boolean = true
)
