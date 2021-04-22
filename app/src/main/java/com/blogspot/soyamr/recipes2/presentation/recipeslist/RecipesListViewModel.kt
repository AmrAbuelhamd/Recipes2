package com.blogspot.soyamr.recipes2.presentation.recipeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blogspot.soyamr.recipes2.domain.entities.SortType
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.blogspot.soyamr.recipes2.domain.entities.onFailure
import com.blogspot.soyamr.recipes2.domain.entities.onSuccess
import com.blogspot.soyamr.recipes2.domain.usecases.GetRecipesListUseCase
import com.blogspot.soyamr.recipes2.domain.usecases.UpdateRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val getRecipesListUseCase: GetRecipesListUseCase,
    private val updateRecipesUseCase: UpdateRecipesUseCase
) :
    ViewModel() {


    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var searchKeyWord = ""
    private var sortBy = SortType.Nothing

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes


    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _isLoading.value = true
            getRecipesListUseCase(sortBy, searchKeyWord)
                .onSuccess {
                    _recipes.value = it
                }.onFailure {
                    _error.value = it.throwable.message
                }
            _isLoading.value = false
        }
    }

    fun updateData() {
        viewModelScope.launch {
            _isLoading.value = true
            updateRecipesUseCase()
                .onSuccess {
                    getData()
                }
                .onFailure {
                    _error.value = it.throwable.message.toString()
                }
            _isLoading.value = false
        }
    }

    fun searchFor(text: String?) {
        text?.let {
            searchKeyWord = it
        }
        getData()
    }

    fun sort(sortTypeBy: SortType) {
        this.sortBy = sortTypeBy
        getData()
    }


}
