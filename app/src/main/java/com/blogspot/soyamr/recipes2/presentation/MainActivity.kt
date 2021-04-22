package com.blogspot.soyamr.recipes2.presentation

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.soyamr.recipes2.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        return super.onMenuOpened(featureId, menu)
        println("heeeeeeeeeeeer")
    }
}