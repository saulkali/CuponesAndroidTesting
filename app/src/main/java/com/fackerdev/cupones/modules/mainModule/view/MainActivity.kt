package com.fackerdev.cupones.modules.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.fackerdev.cupones.R
import com.fackerdev.cupones.common.entitites.CuponEntity
import com.fackerdev.cupones.common.utils.hideKeyBoard
import com.fackerdev.cupones.databinding.ActivityMainBinding
import com.fackerdev.cupones.modules.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var mMainViewModel: MainViewModel
    private lateinit var mBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupViewModel()
        setupObservers()
        setupButtons()
    }



    private fun setupViewModel() {
        mMainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
    private fun setupObservers() {
        /*
        mMainViewModel.getCuponEntity().observe(this){ cupon ->
            if(cupon == null){
                mBinding.tilDescription.hint = getString(R.string.main_hint_description)
                mBinding.tilDescription.isEnabled = true
                mBinding.btnCreate.visibility = View.VISIBLE
            }else {
                mBinding.edDescription.setText(cupon.description)
                var status = getString(if(cupon.isActive) R.string.main_hint_active else R.string.main_hint_description)
                mBinding.tilDescription.hint = status
                mBinding.tilDescription.isEnabled = false
                mBinding.btnCreate.visibility = if(cupon.isActive) View.GONE else View.VISIBLE
            }
        }*/
        mMainViewModel.getSnackbarMsg().observe(this){ msg ->
            Snackbar.make(mBinding.root,msg,Snackbar.LENGTH_SHORT).show()
        }
    }
    private fun setupButtons() {
        mBinding.btnConsult.setOnClickListener {
            //mMainViewModel.consultCuponByCode(mBinding.edCupon.text.toString().trim())
            hideKeyBoard(this,mBinding.root)
        }
        mBinding.btnCreate.setOnClickListener {
            val cuponEntity = CuponEntity(
                code = mBinding.edCupon.text.toString().trim(),
                description = mBinding.edDescription.text.toString().trim()
            )
            //mMainViewModel.addCupon(cuponEntity)
            hideKeyBoard(this,mBinding.root)
        }
    }


}