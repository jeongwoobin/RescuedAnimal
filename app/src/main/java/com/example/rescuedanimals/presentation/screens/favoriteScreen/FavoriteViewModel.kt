package com.example.rescuedanimals.presentation.screens.favoriteScreen

import androidx.lifecycle.ViewModel
import com.example.rescuedanimals.domain.usecase.DeleteFavoriteAnimalUseCase
import com.example.rescuedanimals.domain.usecase.GetFavoriteAnimalUseCase
import com.example.rescuedanimals.domain.usecase.GetRescuedAnimalUseCase
import com.example.rescuedanimals.domain.usecase.InsertFavoriteAnimalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getRescuedAnimalUseCase: GetRescuedAnimalUseCase,
    private val getFavoriteAnimalUseCase: GetFavoriteAnimalUseCase,
    private val insertFavoriteAnimalUseCase: InsertFavoriteAnimalUseCase,
    private val deleteFavoriteAnimalUseCase: DeleteFavoriteAnimalUseCase
) : ViewModel() {
}