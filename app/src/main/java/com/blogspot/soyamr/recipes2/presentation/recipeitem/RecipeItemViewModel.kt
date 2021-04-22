package com.blogspot.soyamr.recipes2.presentation.recipeitem

import androidx.lifecycle.*
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo
import com.blogspot.soyamr.recipes2.domain.entities.onSuccess
import com.blogspot.soyamr.recipes2.domain.usecases.GetRecipeDetailedInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    init {
        viewModelScope.launch {
            _isLoading.value = true
            getRecipeDetailedInfoUseCase(state.get<String>("RecipeId")!!)
                .onSuccess {
                    _recipe.value = it
                }
            _isLoading.value = false
        }
    }

}

