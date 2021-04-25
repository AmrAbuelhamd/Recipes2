package com.blogspot.soyamr.recipes2.presentation.image_viewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blogspot.soyamr.recipes2.domain.RepositoriesContract
import com.blogspot.soyamr.recipes2.utils.Constants
import com.blogspot.soyamr.recipes2.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImageViewerViewModel @Inject constructor(
    private val imageRepository: RepositoriesContract.ImageRepository,
) :
    ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading


    val msg = SingleLiveEvent<String>()

    private val exceptionHandler =
        CoroutineExceptionHandler() { _, exception ->
            viewModelScope.launch(Dispatchers.Main) {
                if (exception.message == Constants.NO_INTERNET_CONNECTION)
                    msg.value = exception.message
                _isLoading.value = false
            }
        }

    fun downloadImage(url: String) {
        _isLoading.value = true
        val job = viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            imageRepository.downloadImage(url).onSuccess {
                withContext(Dispatchers.Main) {
                    msg.value = it
                    _isLoading.value = false
                }
            }
        }
        viewModelScope.launch (exceptionHandler ){
            kotlinx.coroutines.delay(5000)
            if(job.isActive){
                job.cancel()
                msg.value = "Something went wrong"
                _isLoading.value = false
            }
        }

    }

}

