package com.blogspot.soyamr.recipes2.presentation.recipeslist

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.recipes2.R
import com.blogspot.soyamr.recipes2.databinding.FragmentRecipesListBinding
import com.blogspot.soyamr.recipes2.domain.entities.SortType
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.blogspot.soyamr.recipes2.presentation.common.BaseFragment
import com.blogspot.soyamr.recipes2.presentation.recipeslist.recycler.RecipeAdapter
import com.blogspot.soyamr.recipes2.utils.Constants.KEY
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesListFragment :
    BaseFragment<FragmentRecipesListBinding>(FragmentRecipesListBinding::inflate) {

    private val viewModel: RecipesListViewModel by viewModels()
    private lateinit var adapter: RecipeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorState = viewBinding.noInternetStateLayout

        setUpRecycler()
        setUpClickListeners()
        setUpViewModelObservers()
        setUpDialogObserver()
    }

    private fun setUpDialogObserver() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(KEY)
            ?.observe(viewLifecycleOwner) {
                it?.let {
                    viewModel.sort(SortType.getByKey(it))
                }
            }
    }


    private fun setUpRecycler() {
        adapter = RecipeAdapter() { id: String ->
            viewBinding.toolBar.findViewById<SearchView>(R.id.searchBar).clearFocus()
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
        viewModel.noInternetException.observe(viewLifecycleOwner, ::showNoInternetError)
        viewModel.somethingWentWrong.observe(viewLifecycleOwner, ::showSomethingWentWrong)
    }


    private fun updateRecyclerView(recipe: List<Recipe>?) {
        recipe.let {
            viewBinding.mySwipeToRefresh.isRefreshing = false
            adapter.submitList(it)
            showErrorViews(false)
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

            val searchView = toolBar.findViewById<SearchView>(R.id.searchBar)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.searchFor(newText?.trim().toString())
                    return true
                }
            })

            toolBar.setOnMenuItemClickListener {
                if (it.itemId == R.id.more) {
                    findNavController().navigate(RecipesListFragmentDirections.actionRecipesListFragmentToBottomSheetFragment())
                }
                true
            }

            errorState.root.findViewById<MaterialButton>(R.id.refreshButton).setOnClickListener {
                viewModel.updateData()
            }
        }
    }
}