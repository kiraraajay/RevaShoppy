package com.reva.revashoppy.ui.createAccount

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.reva.revashoppy.R
import com.reva.revashoppy.databinding.ActivityCreateAccountBinding
import com.reva.revashoppy.databinding.ActivitySplashScreenBinding
import com.reva.revashoppy.ui.completeProfile.CompleteProfileActivity

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //enableEdgeToEdge()
      /*  setContentView(R.layout.activity_create_account)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        binding.acbSighUpBtn.setOnClickListener {

            val intent = Intent(this, CompleteProfileActivity::class.java)
            startActivity(intent)
            // finish()
            //validation()
        }

    }
}