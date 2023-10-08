package com.example.latihan1

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etUsername:EditText
    private lateinit var etPassword:EditText
    private lateinit var history:TextView

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data:Intent? = result.data
            val returnString:String? = data?.getStringExtra("history")
            history.text = returnString
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        history = findViewById(R.id.lupapassword)
        etUsername = findViewById(R.id.edt_Username)
        etPassword = findViewById(R.id.edt_Password)

        val bundle = intent.extras
        if(bundle != null) {
            etUsername.setText(bundle.getString("username"))
            etPassword.setText(bundle.getString("Password"))
        }

        val btnLogin: Button = findViewById(R.id.btn_Login)
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_Login ->  {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("User",User(etUsername.text.toString(),etPassword.text.toString()))
                resultLauncher.launch(intent)
            }
        }
    }
}