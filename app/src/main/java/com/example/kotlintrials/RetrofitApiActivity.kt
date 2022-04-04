package com.example.kotlintrials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlin.math.log

class RetrofitApiActivity : AppCompatActivity(), RetrofitApiActivityContract.View {

    lateinit var textView : TextView
    lateinit var presenter: RetrofitApiActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_api)

        initView()
        presenter.getDataFromWeb(applicationContext, textView)
    }

    override fun initView() {
        textView = findViewById(R.id.textView)
        presenter = RetrofitApiActivityPresenter(this)
    }
}



