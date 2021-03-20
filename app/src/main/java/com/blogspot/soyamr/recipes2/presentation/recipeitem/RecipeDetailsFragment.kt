package com.blogspot.soyamr.recipes2.presentation.recipeitem

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blogspot.soyamr.recipes2.R
import com.blogspot.soyamr.recipes2.databinding.FragmentRecipeDetailsBinding
import com.blogspot.soyamr.recipes2.domain.model.RecipeDetailedInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment(R.layout.fragment_recipe_details) {

    private val viewModel: RecipeItemViewModel by viewModels()
    private val viewBinding: FragmentRecipeDetailsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModelObservers()


        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        viewBinding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setUpViewModelObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, ::changeLoadingState)
        viewModel.recipe.observe(viewLifecycleOwner, ::bindRecipeData)
    }

    override fun onResume() {
        super.onResume()
        viewBinding.toolbar.title = ""
    }
    private fun bindRecipeData(recipeDetailedInfo: RecipeDetailedInfo?) {
        recipeDetailedInfo?.let {
            with(viewBinding) {
                nameTextView.text = it.name
                detailsDescriptionTextView.text = it.description
                difficultyTextView.text = it.difficulty.toString()
                instructionTextView.text =
                    Html.fromHtml(it.instructions, Html.FROM_HTML_MODE_COMPACT)
                viewPager2.adapter = ViewPagerAdapter(it.images ?: return)
                viewPager2.setPageTransformer(ZoomOutPageTransformer())
                lifecycleScope.launchWhenCreated {
                    delay(500)
                    viewPager2.setCurrentItem(if (it.images.size >= 2) 1 else 0, true)
                }
            }
        }
    }

    private fun changeLoadingState(state: Boolean?) {
        state?.let {
            viewBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

}