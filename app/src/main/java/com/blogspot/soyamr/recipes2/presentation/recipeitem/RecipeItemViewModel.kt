package com.blogspot.soyamr.recipes2.presentation.recipeitem

import androidx.lifecycle.*
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo
import com.blogspot.soyamr.recipes2.domain.usecases.GetRecipeDetailedInfoUseCase
import com.blogspot.soyamr.recipes2.utils.Constants
import com.blogspot.soyamr.recipes2.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeItemViewModel @Inject constructor(
    private val getRecipeDetailedInfoUseCase: GetRecipeDetailedInfoUseCase,
    private val state: SavedStateHandle
) :
    ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _recipe = MutableLiveData<RecipeDetailedInfo>()
    val recipe: LiveData<RecipeDetailedInfo> = _recipe


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
        refresh()
    }

    fun refresh() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getRecipeDetailedInfoUseCase(state.get<String>("RecipeId")!!)
                .onSuccess {
                    withContext(Dispatchers.Main) {
                        _isLoading.value = false
                        _recipe.value = it
                    }
                }
        }
    }

}

