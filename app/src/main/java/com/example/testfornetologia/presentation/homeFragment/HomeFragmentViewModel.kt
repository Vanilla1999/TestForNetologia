package com.example.testfornetologia.presentation.homeFragment

import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.testfornetologia.data.network.model.Json
import com.example.testfornetologia.domain.GetDataFromServerUseCase
import com.example.testfornetologia.utils.ErrorApp
import com.example.testfornetologia.utils.ResponseServer
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HomeFragmentViewModel(
    private val getDataFromServerUseCase: GetDataFromServerUseCase
) : ViewModel() {
    private val _sharedStateFlowError = MutableSharedFlow<ErrorApp<Any?>>(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val sharedStateFlowError = _sharedStateFlowError.asSharedFlow()

    private val coroutineException = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch(Dispatchers.Main) {
            _sharedStateFlowError.emit(ErrorApp.FailureUnknown(throwable.toString()))
        }
    }

    private val _responseNetworkStateFlow: MutableStateFlow<ResponseServer<Json>> =
        MutableStateFlow(ResponseServer.Loading)
    val responseNetworkStateFlow: StateFlow<ResponseServer<Json>> =
        _responseNetworkStateFlow.asStateFlow()

    init {
        getDataFromServer()
    }

    private fun getDataFromServer(){
        Log.d("MainViewModel", "Attraction")
        viewModelScope.launch(Dispatchers.IO + coroutineException) {
            getDataFromServerUseCase.getDataFromServer().collect {
                when (it) {
                    is ResponseServer.Success -> {
                        _responseNetworkStateFlow.emit(ResponseServer.Success(it.value))
                    }
                    is ResponseServer.FailureServer -> {
                        _sharedStateFlowError.emit(
                            ErrorApp.FailureNetwork(
                                it.errorCode,
                                it.errorBody
                            )
                        )
                    }
                    is ResponseServer.Loading ->{
                        _responseNetworkStateFlow.emit(ResponseServer.Loading)
                    }
                    is ResponseServer.FailureEnternet ->{
                        _sharedStateFlowError.emit(ErrorApp.FailureEnternet)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}


class FactoryHomeFragmentViewModel @Inject constructor(
    private val getDataFromServerUseCase: GetDataFromServerUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(getDataFromServerUseCase) as T
    }
}