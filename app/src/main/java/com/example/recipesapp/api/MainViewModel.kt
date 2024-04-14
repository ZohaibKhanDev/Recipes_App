package com.example.recipesapp.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    private val _allCountry=MutableStateFlow<ResultState<Indian>>(ResultState.Loading)
    val allCountry:StateFlow<ResultState<Indian>> =_allCountry.asStateFlow()

    fun getCountry(){
        viewModelScope.launch {
            _allCountry.value=ResultState.Loading
            try {
                val response=repository.getCountry()
                _allCountry.value=ResultState.Success(response)
            }catch (e:Exception){
                _allCountry.value=ResultState.Error(e)
            }
        }
    }
}