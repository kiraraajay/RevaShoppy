package com.reva.revashoppy.ui.splashScreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Handler
import com.reva.revashoppy.databinding.ActivitySplashScreenBinding
import com.reva.revashoppy.ui.login.LoginActivity
import com.reva.revashoppy.utils.sharedPrefrence.AppPreferences
import com.reva.revashoppy.utils.sharedPrefrence.Constant.KEY_ISLOGIN

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var appPreferences : AppPreferences

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using view binding
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enable edge-to-edge display
        enableEdgeToEdge()

        // Set padding to handle system insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        appPreferences= AppPreferences.getInstance(this)

        Handler().postDelayed({
          val isLogin =  appPreferences.getString(KEY_ISLOGIN)
            if (isLogin=="true"){
              /*  startActivity(Intent(this, DashboardActivity::class.java))
                finish()*/
            }
            else{
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }, SPLASH_SCREEN_DELAY)

    }
    companion object {
        private const val SPLASH_SCREEN_DELAY = 2000L // 2 seconds
    }
}
