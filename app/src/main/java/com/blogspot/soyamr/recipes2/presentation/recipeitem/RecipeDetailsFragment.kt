package com.blogspot.soyamr.recipes2.presentation.recipeitem

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.blogspot.soyamr.recipes2.R
import com.blogspot.soyamr.recipes2.data.common.util.toDateString
import com.blogspot.soyamr.recipes2.databinding.FragmentRecipeDetailsBinding
import com.blogspot.soyamr.recipes2.databinding.NoInternetConnectionLayoutBinding
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private val viewModel: RecipeItemViewModel by viewModels()
    private var _binding: FragmentRecipeDetailsBinding? = null
    private val viewBinding get() = _binding!!

    private lateinit var adapter: RecommendedRecipeAdapter
    private lateinit var errorState: NoInternetConnectionLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

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

    private fun showErrorScreen(isShowing: Boolean = true) {
        if (!isShowing) {
            errorState.root.visibility = View.GONE
            viewBinding.viewsOnScreen.visibility = View.VISIBLE
            viewBinding.progressBar.visibility = View.GONE
        } else {
            errorState.root.visibility = View.VISIBLE
            viewBinding.viewsOnScreen.visibility = View.GONE
            viewBinding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun setUpViewModelObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, ::changeLoadingState)
        viewModel.recipe.observe(viewLifecycleOwner, ::bindRecipeData)
        viewModel.noInternetException.observe(viewLifecycleOwner, ::showNoInternetError)
        viewModel.somethingWentWrong.observe(viewLifecycleOwner, ::showSomethingWentWrong)
    }

    private fun showNoInternetError(unit: Unit?) {
        showErrorScreen()
        errorState.root.findViewById<TextView>(R.id.errorTitleTextView).text =
            getText(R.string.no_internet)
        errorState.root.findViewById<TextView>(R.id.errorTitleDetailsTextView).text =
            getText(R.string.no_internet_text)
    }

    private fun showSomethingWentWrong(unit: Unit?) {
        showErrorScreen()
        errorState.root.findViewById<TextView>(R.id.errorTitleTextView).text =
            getText(R.string.something_went_wrong)
        errorState.root.findViewById<TextView>(R.id.errorTitleDetailsTextView).text =
            getText(R.string.something_went_wrong_text)
    }

    override fun onResume() {
        super.onResume()
        viewBinding.toolbar.title = ""
    }

    private fun bindRecipeData(recipeDetailedInfo: RecipeDetailedInfo?) {
        recipeDetailedInfo?.let {
            showErrorScreen(false)
            with(viewBinding) {
                nameTextView.text = it.name
                detailsDescriptionTextView.text = it.description
                ratingBar.rating = it.difficulty.toFloat()
                dateTextView.text = it.lastUpdated.toDateString()
                instructionTextView.text =
                    Html.fromHtml(it.instructions, Html.FROM_HTML_MODE_COMPACT)
                viewPager.adapter = ViewPagerAdapter(it.images)
                TabLayoutMediator(dots, viewPager) { tab, _ ->
                    viewPager.setCurrentItem(tab.position, true)
                }.attach()
                if (it.similar.isNullOrEmpty()) {
                    recyclerView.visibility = View.GONE
                    recommendedText.visibility = View.INVISIBLE
                } else
                    adapter.submitList(it.similar)
            }
        }
    }

    private fun changeLoadingState(state: Boolean?) {
        state?.let {
            viewBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

}