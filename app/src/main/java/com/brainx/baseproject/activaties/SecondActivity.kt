package com.brainx.baseproject.activaties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brainx.baseproject.R
import kotlinx.android.synthetic.main.activity_second_actiivity.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_actiivity)

        close_btn_2.setOnClickListener {
            setResult(3001)
            finish()
        }
    }
}