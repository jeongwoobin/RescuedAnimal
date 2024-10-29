package com.example.rescuedanimals.presentation.screens.rescuedAnimalScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rescuedanimals.domain.entity.Animal
import com.example.rescuedanimals.domain.entity.Event
import com.example.rescuedanimals.domain.entity.Result
import com.example.rescuedanimals.domain.entity.Status
import com.example.rescuedanimals.domain.usecase.DeleteFavoriteAnimalUseCase
import com.example.rescuedanimals.domain.usecase.SelectFavoriteAnimalUseCase
import com.example.rescuedanimals.domain.usecase.GetRescuedAnimalUseCase
import com.example.rescuedanimals.domain.usecase.InsertFavoriteAnimalUseCase
import com.example.rescuedanimals.presentation.utils.Utils
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RescuedAnimalViewModel @Inject constructor(
    private val getRescuedAnimalUseCase: GetRescuedAnimalUseCase,
    private val selectFavoriteAnimalUseCase: SelectFavoriteAnimalUseCase,
    private val insertFavoriteAnimalUseCase: InsertFavoriteAnimalUseCase,
    private val deleteFavoriteAnimalUseCase: DeleteFavoriteAnimalUseCase
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getRescuedAnimal(refresh = true)
        }
    }

    private var pageNo: Int = 1

    private val _resultState: MutableStateFlow<Result<Any>> = MutableStateFlow(Result.success())
    val resultState: StateFlow<Result<Any>>
        get() = _resultState.asStateFlow()

    private val _snackbarEvent: MutableStateFlow<Event<String?>> = MutableStateFlow(Event(null))
    val snackbarEvent: StateFlow<Event<String?>>
        get() = _snackbarEvent.asStateFlow()

    private val _rescuedAnimalList: MutableStateFlow<List<Animal>> = MutableStateFlow(emptyList())
    val rescuedAnimalList: StateFlow<List<Animal>>
        get() = _rescuedAnimalList.asStateFlow()

    private fun updateSnackbarEvent(text: String) {
        _snackbarEvent.update { Event(text) }
    }

    private fun setResultState(state: Result<Any>) {
        _resultState.update { state }
    }

    private fun updatePage(refresh: Boolean = false) {
        if (refresh) pageNo = 1
        else {
            pageNo += if (pageNo != 1) 1 else 2
        }
    }

    suspend fun getRescuedAnimal(refresh: Boolean = false) {
        if (refresh) updatePage(refresh = refresh)
        viewModelScope.launch(Dispatchers.IO) {
            getRescuedAnimalUseCase(
                pageNo = pageNo,
                numOfRows = if (pageNo != 1) 20 else 40
            ).onStart { emit(Result.loading(null)) }
                .collect { result ->
                    Logger.d("getRescuedAnimal result status: ${result.status}")
                    setResultState(result)
                    when (result.status) {
                        Status.SUCCESS -> {
                            result.data?.let { data ->
                                if (data.items.item.isNotEmpty())
                                    _rescuedAnimalList.update { if (refresh) data.items.item else rescuedAnimalList.value + data.items.item }
                                else {
                                    // 데이터가 없습니다
                                    updateSnackbarEvent(Utils.snackBarContent(content = "마지막 데이터 입니다."))
                                }
                                updatePage()
                            }
                        }

                        Status.LOADING -> {}
                        else -> {
                            updateSnackbarEvent(
                                Utils.snackBarContent(
                                    isError = true,
                                    content = result.message.toString()
                                )
                            )
                        }
                    }
                }
        }
    }

    suspend fun insertFavoriteAnimal(index: Int, animal: Animal) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteAnimalUseCase(
                favoriteAnimal = animal
            ).onStart { emit(Result.loading(null)) }
                .collect { result ->
                    Logger.d("insertFavoriteAnimal result status: ${result.status}")
                    setResultState(result)
                    when (result.status) {
                        Status.SUCCESS -> {
                            result.data?.let { data ->
                                if (data) {
                                    _rescuedAnimalList.update { list ->
                                        val updateList = list.toMutableList()
                                        updateList[index] =
                                            updateList[index].copy(kindCd = "favorite!")
                                        updateList
                                    }
                                    updateSnackbarEvent(Utils.snackBarContent(content = "저장에 성공했습니다."))
                                } else {
                                    // 데이터가 없습니다
                                    updateSnackbarEvent(
                                        Utils.snackBarContent(
                                            isError = true,
                                            content = "저장에 실패했습니다."
                                        )
                                    )
                                }
                            }
                        }

                        Status.LOADING -> {}
                        else -> {
                            updateSnackbarEvent(
                                Utils.snackBarContent(
                                    isError = true,
                                    content = result.message.toString()
                                )
                            )
                        }
                    }
                }
        }
    }
}