package com.mitsinsar.mvvmexample.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mitsinsar.mvvmexample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Int.MAX_VALUE
    }
}
