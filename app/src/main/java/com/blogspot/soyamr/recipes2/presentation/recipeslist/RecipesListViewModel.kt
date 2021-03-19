package com.blogspot.soyamr.recipes2.presentation.recipeslist

import androidx.lifecycle.*
import com.blogspot.soyamr.recipes2.domain.interactors.GetRecipesListUseCase
import com.blogspot.soyamr.recipes2.domain.interactors.QueryRecipes
import com.blogspot.soyamr.recipes2.domain.interactors.UpdateRecipesUseCase
import com.blogspot.soyamr.recipes2.domain.model.onFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val getRecipesListUseCase: GetRecipesListUseCase,
    private val updateRecipesUseCase: UpdateRecipesUseCase,
    private val queryRecipes: QueryRecipes
) :
    ViewModel() {


    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val searchKeyWord = MutableLiveData("")

    val recipes = Transformations.switchMap(searchKeyWord) { string ->
        if (string.isNullOrEmpty())
            getRecipesListUseCase()
                .onStart { _isLoading.value = true }
                .asLiveData()
        else
            queryRecipes(string.toLowerCase(Locale.ROOT))
                .onStart { _isLoading.value = true }
                .asLiveData()
    }


    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error


    init {
        viewModelScope.launch {
            _isLoading.value = true
            updateRecipesUseCase()
                .onFailure {
                    _error.value = it.throwable.message.toString()
                }
            _isLoading.value = false
        }
    }

    fun updateData() {
        viewModelScope.launch {
            _isLoading.value = true
            updateRecipesUseCase()
                .onFailure {
                    _error.value = it.throwable.message.toString()
                }
            _isLoading.value = false
        }
    }

    fun searchFor(text: String?) {
        text?.let {
            searchKeyWord.value = it
        }
    }


}
