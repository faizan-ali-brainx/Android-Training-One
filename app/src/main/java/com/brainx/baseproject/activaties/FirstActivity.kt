package com.brainx.baseproject.activaties

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brainx.baseproject.R
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        close_btn_1.setOnClickListener {
            setResult(3000)
            finish()
        }
    }

}