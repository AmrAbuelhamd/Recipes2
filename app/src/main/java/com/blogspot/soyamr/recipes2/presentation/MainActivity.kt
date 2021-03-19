package com.blogspot.soyamr.recipes2.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.soyamr.recipes2.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blogspot.soyamr.recipes2.databinding.ActivityMainBinding
import com.blogspot.soyamr.recipes2.domain.Repository
import com.blogspot.soyamr.recipes2.domain.model.RecipeInfo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
}