package com.blogspot.soyamr.recipes2.presentation.recipeslist

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blogspot.soyamr.recipes2.R
import com.blogspot.soyamr.recipes2.databinding.FragmentRecipesListBinding
import com.blogspot.soyamr.recipes2.domain.model.RecipeInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesListFragment : Fragment(R.layout.fragment_recipes_list) {

    private val viewModel: RecipesListViewModel by viewModels()
    private val viewBinding: FragmentRecipesListBinding by viewBinding()
    private lateinit var adapter: RecipeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        setUpClickListeners()
        setUpViewModelObservers()
    }


    private fun setUpRecycler() {
        adapter = RecipeAdapter() { id: String ->
            findNavController()
                .navigate(
                    RecipesListFragmentDirections.actionRecipesListFragmentToRecipeDetailsFragment(
                        id
                    )
                )
        }
        viewBinding.list.adapter = adapter
    }


    private fun setUpViewModelObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, ::changeLoadingState)
        viewModel.recipes.observe(viewLifecycleOwner, ::updateRecyclerView)
        viewModel.error.observe(viewLifecycleOwner, ::showError)
    }


    private fun showError(error: String?) {
        error?.let {
            if (error.isNotEmpty()) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateRecyclerView(recipeInfo: List<RecipeInfo>?) {
        recipeInfo.let {
            viewBinding.mySwipeToRefresh.isRefreshing = false
            adapter.submitList(it)
        }
    }

    private fun changeLoadingState(state: Boolean?) {
        state?.let {
            viewBinding.mySwipeToRefresh.isRefreshing = it
        }
    }

    private fun setUpClickListeners() {
        with(viewBinding) {
            mySwipeToRefresh.setOnRefreshListener {
                viewModel.updateData()
            }
            with(searchEditText){
                doAfterTextChanged { text ->
                    viewModel.searchFor(text?.trim().toString())
                }
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        clearFocus()
                    }
                    false
                }
            }
        }
    }

}