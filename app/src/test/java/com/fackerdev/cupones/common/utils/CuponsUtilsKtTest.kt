package com.fackerdev.cupones.common.utils

import org.junit.Assert.*
import org.junit.Test
import com.fackerdev.cupones.R
import com.fackerdev.cupones.common.entitites.CuponEntity

class CuponsUtilsKtTest{
    @Test
    fun validateTextCodeSuccessTest(){
        val code:String = "WELCOM"
        assertTrue(validateTextCode(code))
    }
    @Test
    fun validateTextCodeEmptyFailTest(){
        val code:String = ""
        assertFalse(validateTextCode(code))
    }
    @Test
    fun validateTextCodeMinLengthTest(){
        val code:String = "MJ"
        assertFalse(validateTextCode(code))
    }
    @Test
    fun validateTextCodeMaxLengthTest(){
        val code:String = "MJJJD HDJASHDHASHDHHDAH"
        assertFalse(validateTextCode(code))
    }
    @Test
    fun getMsgErrorByCodeNullTest(){
        val errorCode = null
        val expectedValue = R.string.error_unknow
        val result = getMsgErrorByCode(errorCode = errorCode)
        assertEquals("error al evaluar un cupon null.",expectedValue, result)
    }
    @Test
    fun getMsgErrorByCodeExistTest(){
        val errorCode = Constans.ERROR_EXISTS
        val expectedValue = R.string.error_unique_code
        val result = getMsgErrorByCode(errorCode = errorCode)
        assertEquals("error al evaluar un cupon existente.",expectedValue, result)
    }
    @Test
    fun getMsgErrorByCodeInvalidLengthTest(){
        val errorCode = Constans.ERROR_LENGTH
        val expectedValue = R.string.error_invalid_length
        val result = getMsgErrorByCode(errorCode = errorCode)
        assertEquals("error al evaluar la longitud del cupon.",expectedValue, result)
    }

    @Test
    fun checkCouponIsNotNullTest(){
        val coupon = CuponEntity(code = "helloworld", description = "hola mundo texto de prueba")
        assertNotNull("la entidad es null",coupon)
    }
    @Test
    fun checkGroupSuccessTest(){
        val arrayDataName = arrayOf("don juan","miguel","lopez") //valor actual
        val expectedValue = arrayOf("don juan","miguel","lopez") //valor esperado
        assertArrayEquals("Los arreglos deberian ser silimiares revisa tus elementos",expectedValue,arrayDataName)
    }

    @Test
    fun checkNullCouponTest(){
        val coupon = null
        assertNull("el cupon deberia ser null",coupon)
    }

    @Test
    fun checkGroupNotEquals(){
        val arrayDataName = arrayOf("don juan","miguel","lopez") //valor actual
        val expectedValue = arrayOf("pancho","maria","lopez doriga") //valor esperado
        assertNotEquals("Estos arreglos son similares",arrayDataName,expectedValue)
    }

}