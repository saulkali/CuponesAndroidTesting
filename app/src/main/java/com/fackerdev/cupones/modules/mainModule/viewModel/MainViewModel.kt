package com.fackerdev.cupones.modules.mainModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fackerdev.cupones.R
import com.fackerdev.cupones.common.entitites.CuponEntity
import com.fackerdev.cupones.common.utils.getMsgErrorByCode
import com.fackerdev.cupones.modules.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel :ViewModel(){
    private val respository = MainRepository()

    val cupon = MutableLiveData(CuponEntity());

    private val hideKeyboard = MutableLiveData<Boolean>();
    fun isHideKeyboard() = hideKeyboard

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    fun consultCuponByCode(){
        cupon.value?.code?.let { code ->
            viewModelScope.launch {
                hideKeyboard.value = true;
                cupon.value = respository.consultCuponByCode(code) ?: CuponEntity(code = code, isActive = false)
            }
        }
    }

    fun addCupon(){
        cupon.value?.let { cuponEntity ->
            viewModelScope.launch {
                hideKeyboard.value = true
                try{
                    cuponEntity.isActive = true
                    respository.addCupon(cuponEntity)
                    consultCuponByCode()
                    snackbarMsg.value = R.string.main_add_success
                }catch (e:Exception){
                    snackbarMsg.value = getMsgErrorByCode(e.message)
                }
            }
        }

    }
}