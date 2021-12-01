package com.brainx.baseproject.activaties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brainx.baseproject.R
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        close_btn_3.setOnClickListener {
            setResult(3002)
            finish()
        }
    }
}