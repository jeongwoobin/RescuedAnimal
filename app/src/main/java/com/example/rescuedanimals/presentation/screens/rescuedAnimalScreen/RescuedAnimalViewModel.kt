package com.example.rescuedanimals.presentation.screens.rescuedAnimalScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rescuedanimals.domain.entity.Animal
import com.example.rescuedanimals.domain.entity.Status
import com.example.rescuedanimals.domain.usecase.DeleteFavoriteAnimalUseCase
import com.example.rescuedanimals.domain.usecase.GetFavoriteAnimalUseCase
import com.example.rescuedanimals.domain.usecase.GetRescuedAnimalUseCase
import com.example.rescuedanimals.domain.usecase.InsertFavoriteAnimalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RescuedAnimalViewModel @Inject constructor(
    private val getRescuedAnimalUseCase: GetRescuedAnimalUseCase,
    private val getFavoriteAnimalUseCase: GetFavoriteAnimalUseCase,
    private val insertFavoriteAnimalUseCase: InsertFavoriteAnimalUseCase,
    private val deleteFavoriteAnimalUseCase: DeleteFavoriteAnimalUseCase
) : ViewModel() {

    init {
        getRescuedAnimal()
    }

    private val _pageNo: MutableLiveData<Int> = MutableLiveData(0)
    val pageNo: LiveData<Int>
        get() = _pageNo

    private val _numOfRows: MutableLiveData<Int> = MutableLiveData(0)
    val numOfRows: LiveData<Int>
        get() = _numOfRows

    private val _rescuedAnimalList: MutableStateFlow<List<Animal>> = MutableStateFlow(emptyList())
    val rescuedAnimalList: StateFlow<List<Animal>>
        get() = _rescuedAnimalList

    private fun getRescuedAnimal() {
        viewModelScope.launch(Dispatchers.IO) {
            getRescuedAnimalUseCase(
                pageNo = pageNo.value ?: 0,
                numOfRows = numOfRows.value ?: 0
            ).let { result ->
                if (result.status == Status.SUCCESS) {
                    result.data?.let { data ->
                        if (data.isNotEmpty())
                            _rescuedAnimalList.value += data
                        else {
                            // 데이터가 없습니다
                        }
                    }
                } else {
                    //fail
                }
            }
        }
    }
}