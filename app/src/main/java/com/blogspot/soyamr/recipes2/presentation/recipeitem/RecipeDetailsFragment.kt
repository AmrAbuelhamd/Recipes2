package com.blogspot.soyamr.recipes2.presentation.recipeitem

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blogspot.soyamr.recipes2.R
import com.blogspot.soyamr.recipes2.databinding.FragmentRecipeDetailsBinding
import com.blogspot.soyamr.recipes2.domain.model.RecipeDetailedInfo
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment(R.layout.fragment_recipe_details) {

    private val viewModel: RecipeItemViewModel by viewModels()
    private val viewBinding: FragmentRecipeDetailsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModelObservers()
    }

    private fun setUpViewModelObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, ::changeLoadingState)
        viewModel.recipe.observe(viewLifecycleOwner, ::bindRecipeData)
    }

    private fun bindRecipeData(recipeDetailedInfo: RecipeDetailedInfo?) {
        recipeDetailedInfo?.let {
            with(viewBinding) {
                nameTextView.text = it.name
                detailsDescriptionTextView.text = it.description
                diffecultyTextView.text = it.difficulty.toString()
                instrucitonTextView.text = it.instructions
                Picasso.get().load(it.images?.get(0)).into(imageView);
            }
        }
    }

    private fun changeLoadingState(state: Boolean?) {
        state?.let {
            viewBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

}