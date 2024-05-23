package com.example.springtak

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.springtak.databinding.ActivityLoginBinding
import com.example.springtak.utils.PrefManager
import com.example.springtak.utils.isValidEmail
import com.example.springtak.utils.network.LoginReq
import com.example.springtak.utils.network.AppViewModel
import com.example.springtak.utils.network.STATUS

class LoginActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: AppViewModel

    private lateinit var email: String
    private lateinit var pass: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        prefManager = PrefManager(this)


        loginViewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        if (prefManager.getStringData("TOKEN") != null) {
            toNextScreen()
            finish()
        }

        setOnClicked()

        setObserver()

    }

    private fun setOnClicked() {

        binding.idButton.setOnClickListener {

            email = binding.idEditEmail.text.toString()
            pass = binding.idEditPass.text.toString()

            if (!email.isValidEmail()) {
                Toast.makeText(this, "Invalid Email Id", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass.isEmpty() || pass.isBlank()) {
                Toast.makeText(this, "Please enter pass", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            loginViewModel.doLogin(LoginReq(email,pass))
        }
    }

    private fun setObserver() {

        loginViewModel.loginRes.observe(this) {
            prefManager.saveStringData("TOKEN", it)
            Log.d("AXE","Token : $it")
            toNextScreen()
        }
        loginViewModel.status.observe(this) {

            when(it){
               STATUS.LOADING -> {
                   binding.idButton.visibility = View.GONE
                   binding.idProgress.visibility = View.VISIBLE
               }
                STATUS.ERROR -> {
                    binding.idButton.visibility = View.VISIBLE
                    binding.idProgress.visibility = View.GONE
                    Toast.makeText(this, "Somthing is wrong", Toast.LENGTH_SHORT).show()
                }
                STATUS.DONE -> {
                    if (prefManager.getStringData("TOKEN") != null) {
                        toNextScreen()
                    }
                }
                else -> {}
            }
        }
    }

    private fun toNextScreen() {
        Intent(this, HomeActivity::class.java).apply {
            startActivity(this)
        }
    }
}

//eve.holt@reqres.in