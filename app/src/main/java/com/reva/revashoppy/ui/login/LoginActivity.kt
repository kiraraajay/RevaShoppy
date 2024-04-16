package com.reva.revashoppy.ui.login

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.reva.revashoppy.base.BaseActivity
import com.reva.revashoppy.common.AppState
import com.reva.revashoppy.common.DialogUtils.Companion.showProgress
import com.reva.revashoppy.databinding.ActivityLoginBinding
import com.reva.revashoppy.ui.createAccount.CreateAccountActivity
import com.reva.revashoppy.utils.sharedPrefrence.AppPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getInjectViewBinding() = ActivityLoginBinding.inflate(layoutInflater)
    private lateinit var progress: Dialog

    private val viewModel: LoginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    private lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        progress = showProgress(this)

        binding.acbSighInBtn.setOnClickListener {

            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
            // finish()
            //validation()
        }
        /*  appPreferences = AppPreferences.getInstance(this)

          setUpViewModelObserver()*/


    }

    private fun validation() {

        /* if (!checkNumber(binding.etMobileNo.text.toString())) {
             Toast.makeText(this, "Please Enter Correct Detail", Toast.LENGTH_SHORT).show()
             return
         }

         if (!validateNumber(binding.etMobileNo.text.toString())) {
             Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show()
             return
         }

         if (!isEmptyOrLength(binding.etPassword.text.toString())) {
             Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show()
             return
         }


         viewModel.login(binding.etMobileNo.text.toString(), binding.etPassword.text.toString())
        // progress.show()*/

    }


    /*  override fun setUpViewModelObserver() {
          super.setUpViewModelObserver()
          viewModel.loginResult.observe(this) { response ->
              when (response) {
                  is AppState.Loading -> {
                      progress.show()
                  }

                  is AppState.APILoginSuccess -> {
                      progress.dismiss()

                    *//*  appPreferences.saveString(Constant.KEY_ISLOGIN, "true")
                    appPreferences.saveString(Constant.userId, response.login.userId.toString())
                    appPreferences.saveString(Constant.mobileNo, response.login.mobile.toString())
                    appPreferences.saveString(Constant.name, response.login.name.toString())
                    appPreferences.saveString(Constant.email, response.login.email.toString())
                    appPreferences.saveString(
                        Constant.designation,
                        response.login.designation.toString()
                    )
*//*

                 *//*   val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()*//*
                    // Toast.makeText(this,response.login.msg, Toast.LENGTH_SHORT).show()

                }

                is AppState.NoInternetConnection -> {
                    progress.dismiss()
                    Toast.makeText(this, "Please check your connection", Toast.LENGTH_SHORT).show()
                }

                is AppState.UnknownError -> {
                    progress.dismiss()
                    Toast.makeText(this, "An error occured", Toast.LENGTH_SHORT).show()
                }

                is AppState.SeverError -> {
                    progress.dismiss()
                    Toast.makeText(this@LoginActivity, response.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }

        }
    }*/

    override fun isInternetCheck(boolean: Boolean) {
        /*  runOnUiThread {
              if (boolean) {
                  binding.clNoInternet.visibility = View.GONE
                  binding.clMainScreen.visibility = View.VISIBLE
                  // Toast.makeText(this,"Internet On",Toast.LENGTH_SHORT).show()
              } else {
                  binding.clNoInternet.visibility = View.VISIBLE
                  binding.clMainScreen.visibility = View.GONE
                  // Toast.makeText(this,"Internet Off",Toast.LENGTH_SHORT).show()
              }
          }*/
    }

}
