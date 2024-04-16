package com.example.recipesapp.api

import Canadian
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.recipesapp.Worlds.World
import com.example.recipesapp.database.Fav
import com.example.recipesapp.detail.Detail
import com.example.recipesapp.search.Search
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _allCountry = MutableStateFlow<ResultState<Indian>>(ResultState.Loading)
    val allCountry: StateFlow<ResultState<Indian>> = _allCountry.asStateFlow()

    private val _allCanadian = MutableStateFlow<ResultState<Canadian>>(ResultState.Loading)
    val allCanadian: StateFlow<ResultState<Canadian>> = _allCanadian.asStateFlow()

    private val _allWorld = MutableStateFlow<ResultState<World>>(ResultState.Loading)
    val allWorld: StateFlow<ResultState<World>> = _allWorld.asStateFlow()

    private val _allDetail = MutableStateFlow<ResultState<Detail>>(ResultState.Loading)
    val allDetail: StateFlow<ResultState<Detail>> = _allDetail.asStateFlow()

    private val _allFav = MutableStateFlow<ResultState<List<Fav>>>(ResultState.Loading)
    val allFav: StateFlow<ResultState<List<Fav>>> = _allFav.asStateFlow()

    private val _allInsert = MutableStateFlow<ResultState<Unit>>(ResultState.Loading)
    val allInsert: StateFlow<ResultState<Unit>> = _allInsert.asStateFlow()

    private val _allSearch=MutableStateFlow<ResultState<Search>>(ResultState.Loading)
    val allSearch:StateFlow<ResultState<Search>> = _allSearch.asStateFlow()
    fun getAllFav() {
        viewModelScope.launch {
            _allFav.value = ResultState.Loading
            try {
                val response = repository.getAllFav()
                _allFav.value = ResultState.Success(response)
            } catch (e: Exception) {
                _allFav.value = ResultState.Error(e)
            }
        }
    }

    fun Insert(fav: Fav) {
        viewModelScope.launch {
            _allInsert.value = ResultState.Loading
            try {
                val response = repository.Insert(fav)
                _allInsert.value = ResultState.Success(response)
            } catch (e: Exception) {
                _allInsert.value = ResultState.Error(e)
            }
        }
    }

    fun getDetail(id: String) {
        viewModelScope.launch {
            _allDetail.value = ResultState.Loading
            try {
                val response = repository.Details(id)
                _allDetail.value = ResultState.Success(response)
            } catch (e: Exception) {
                _allDetail.value = ResultState.Error(e)
            }
        }
    }

    fun getCountry() {
        viewModelScope.launch {
            _allCountry.value = ResultState.Loading
            try {
                val response = repository.getCountry()
                _allCountry.value = ResultState.Success(response)
            } catch (e: Exception) {
                _allCountry.value = ResultState.Error(e)
            }
        }
    }

    fun getCanadian() {
        viewModelScope.launch {
            _allCanadian.value = ResultState.Loading
            try {
                val response = repository.getCanadian()
                _allCanadian.value = ResultState.Success(response)
            } catch (e: Exception) {
                _allCanadian.value = ResultState.Error(e)
            }
        }
    }

    fun getWorld() {
        viewModelScope.launch {
            _allWorld.value = ResultState.Loading
            try {
                val response = repository.getWorld()
                _allWorld.value = ResultState.Success(response)
            } catch (e: Exception) {
                _allWorld.value = ResultState.Error(e)
            }
        }
    }

    fun getSearch(query: String){
        viewModelScope.launch {
            _allSearch.value=ResultState.Loading
            try {
                val response=repository.Search(query)
                _allSearch.value=ResultState.Success(response)
            }catch (e:Exception){
                _allSearch.value=ResultState.Error(e)
            }
        }
    }
}