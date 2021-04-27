package com.blogspot.soyamr.recipes2.presentation.recipeitem

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.blogspot.soyamr.recipes2.R
import com.blogspot.soyamr.recipes2.data.common.util.toDateString
import com.blogspot.soyamr.recipes2.databinding.FragmentRecipeDetailsBinding
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo
import com.blogspot.soyamr.recipes2.presentation.common.BaseFragment
import com.blogspot.soyamr.recipes2.presentation.recipeitem.adapters.PhotosViewPagerAdapter
import com.blogspot.soyamr.recipes2.presentation.recipeitem.adapters.RecommendedRecipeAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment :
    BaseFragment<FragmentRecipeDetailsBinding>(FragmentRecipeDetailsBinding::inflate) {

    private val viewModel: RecipeItemViewModel by viewModels()

    private lateinit var adapter: RecommendedRecipeAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorState = viewBinding.noInternetStateLayout

        setUpListeners()
        setUpViewModelObservers()
        setUpRecycler()
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        viewBinding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setUpListeners() {
        errorState.root.findViewById<MaterialButton>(R.id.refreshButton).setOnClickListener {
            viewModel.refresh()
        }

        viewBinding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.like) {
                showMessage(R.string.thanks)
            } else if (it.itemId == R.id.share) {
                viewModel.recipe.value?.name?.let {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, getString(R.string.letsCookThis, it))
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
            }
            true
        }
    }

    private fun setUpRecycler() {
        adapter = RecommendedRecipeAdapter() { id: String ->
            findNavController()
                .navigate(
                    RecipeDetailsFragmentDirections.actionRecipeDetailsFragmentSelf(
                        id
                    )
                )
        }
        viewBinding.recyclerView.adapter = adapter
    }

    private fun setUpViewModelObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, ::changeLoadingState)
        viewModel.recipe.observe(viewLifecycleOwner, ::bindRecipeData)
        viewModel.noInternetException.observe(viewLifecycleOwner, ::showNoInternetError)
        viewModel.somethingWentWrong.observe(viewLifecycleOwner, ::showSomethingWentWrong)
    }

    override fun onResume() {
        super.onResume()
        viewBinding.toolbar.title = ""
    }

    private fun bindRecipeData(recipeDetailedInfo: RecipeDetailedInfo?) {
        recipeDetailedInfo?.let {
            showErrorViews(false)
            with(viewBinding) {
                nameTextView.text = it.name
                detailsDescriptionTextView.text = it.description
                ratingBar.rating = it.difficulty.toFloat()
                dateTextView.text = it.lastUpdated.toDateString()
                instructionTextView.text =
                    Html.fromHtml(it.instructions, Html.FROM_HTML_MODE_COMPACT)
                viewPager.adapter = PhotosViewPagerAdapter(it.images) { url: String ->
                    findNavController()
                        .navigate(
                            RecipeDetailsFragmentDirections.actionRecipeDetailsFragmentToImageViewerFragment(
                                url
                            )
                        )
                }

                TabLayoutMediator(dots, viewPager) { tab, _ ->
                    viewPager.setCurrentItem(tab.position, true)
                }.attach()
                if (it.similar.isNullOrEmpty()) {
                    recyclerView.visibility = View.GONE
                    recommendedText.visibility = View.INVISIBLE
                } else {
                    adapter.submitList(it.similar)
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