package com.wdretzer.nasaprojetointegrador.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wdretzer.nasaprojetointegrador.data.NasaRequest
import com.wdretzer.nasaprojetointegrador.repository.NasaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NasaViewModel (private val repository: NasaRepository = NasaRepository.instance) : ViewModel() {

    private val _error: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    private val _success: MutableLiveData<NasaRequest> = MutableLiveData()
    val success: LiveData<NasaRequest>
    get() = _success

    fun request(search: String) = viewModelScope.launch(Dispatchers.Main){
        repository
            .requestData(search)
            .catch { _error.value = true }
            .collect {
                _success.value = it
            }
    }
}
