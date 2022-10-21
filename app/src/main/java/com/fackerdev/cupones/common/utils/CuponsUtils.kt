package com.fackerdev.cupones.common.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.fackerdev.cupones.R

fun validateTextCode(code: String) = !(code.length < 5 || code.length > 10)

fun getMsgErrorByCode(errorCode:String?) = when(errorCode){
    Constans.ERROR_EXISTS -> R.string.error_unique_code
    Constans.ERROR_LENGTH -> R.string.error_invalid_length
    else -> R.string.error_unknow
}

fun hideKeyBoard(context:Context,view:View){
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken,0)
}