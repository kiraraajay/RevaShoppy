package com.reva.revashoppy.ui.dashboard

import android.app.Dialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.reva.revashoppy.R
import com.reva.revashoppy.base.BaseActivity
import com.reva.revashoppy.base.BaseActivityHelper
import com.reva.revashoppy.common.DialogUtils
import com.reva.revashoppy.databinding.ActivityDashboardBinding
import com.reva.revashoppy.databinding.ActivityLoginBinding
import com.reva.revashoppy.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>(){
    override fun getInjectViewBinding() = ActivityDashboardBinding.inflate(layoutInflater)

    private lateinit var progress: Dialog

    private val viewModel:    LoginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    private lateinit var baseActivityHelper: BaseActivityHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progress = DialogUtils.showProgress(this)

        baseActivityHelper = BaseActivityHelper(this)




    }

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