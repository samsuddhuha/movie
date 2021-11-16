package com.learn.movie.feature.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learn.movie.R
import com.learn.movie.support.dialog.DialogSuccess
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val dialogSuccess  by lazy { DialogSuccess(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupView()
    }

    private fun setupView() {
        btn_login.setOnClickListener {
            dialogSuccess.showDialog()
        }
    }
}