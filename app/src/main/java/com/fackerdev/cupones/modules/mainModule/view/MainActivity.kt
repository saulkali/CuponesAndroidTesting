package com.fackerdev.cupones.modules.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.fackerdev.cupones.BR
import com.fackerdev.cupones.R
import com.fackerdev.cupones.common.entitites.CuponEntity
import com.fackerdev.cupones.common.utils.hideKeyBoard
import com.fackerdev.cupones.databinding.ActivityMainBinding
import com.fackerdev.cupones.modules.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityMainBinding
    //private lateinit var mMainViewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        //mMainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val vm:MainViewModel by viewModels()
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.viewModel,vm)
    }

    private fun setupObservers() {
        mBinding.viewModel?.let {
            it.cupon.observe(this@MainActivity){ cupon ->
                mBinding.isActive = cupon != null && cupon.isActive
            }

            it.getSnackbarMsg().observe(this@MainActivity){ msg ->
                Snackbar.make(mBinding.root,msg,Snackbar.LENGTH_SHORT).show()
            }
            it.isHideKeyboard().observe(this@MainActivity){ isHide ->
                if (isHide) hideKeyBoard(this@MainActivity,mBinding.root)
            }
        }

    }
}