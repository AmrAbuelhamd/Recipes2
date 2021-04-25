package com.blogspot.soyamr.recipes2.presentation.recipeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blogspot.soyamr.recipes2.domain.RepositoriesContract
import com.blogspot.soyamr.recipes2.domain.entities.SortType
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.blogspot.soyamr.recipes2.utils.Constants
import com.blogspot.soyamr.recipes2.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val recipesRepository: RepositoriesContract.RecipesRepository
) :
    ViewModel() {


    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var searchKeyWord = ""

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes


    val somethingWentWrong = SingleLiveEvent<Unit>()
    val noInternetException = SingleLiveEvent<Unit>()

    private val exceptionHandler =
        CoroutineExceptionHandler() { _, exception ->
            viewModelScope.launch(Dispatchers.Main) {
                if (exception.message == Constants.NO_INTERNET_CONNECTION)
                    noInternetException()
                else
                    somethingWentWrong()
                _isLoading.value = false
            }
        }

    init {
        _isLoading.value = true
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            recipesRepository.getRecipes(searchKeyWord)
                .onSuccess {
                    withContext(Dispatchers.Main) {
                        _isLoading.value = false
                        _recipes.value = it
                    }
                }
        }
    }

    fun updateData() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            recipesRepository.updateRecipes()
            getData()
        }
    }


    fun searchFor(text: String?) {
        text?.let {
            searchKeyWord = it
        }
        getData()
    }

    fun sort(sortTypeBy: SortType) {
        when (sortTypeBy) {
            SortType.ByName -> _recipes.value = _recipes.value!!.sortedBy { it.name }
            SortType.ByDate -> _recipes.value = _recipes.value!!.sortedBy { it.lastUpdated }
            SortType.Nothing -> {
            }
        }
    }


}
