package com.example.rescuedanimals.presentation.screens.rescuedAnimalScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rescuedanimals.domain.entity.Animal
import com.example.rescuedanimals.presentation.component.ScreenDivider
import com.example.rescuedanimals.presentation.component.VectorIcon
import com.example.rescuedanimals.presentation.navigation.Screen
import com.example.rescuedanimals.ui.theme.Primary_Red_500
import com.example.rescuedanimals.ui.theme.Text_600

@Composable
fun RescuedAnimalScreen(
    navController: NavController,
    rescuedAnimalViewModel: RescuedAnimalViewModel = hiltViewModel()
) {

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(it)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                VectorIcon(
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.FavoriteScreen.route)
                    },
                    vector = Icons.Filled.Favorite,
                    tint = Primary_Red_500,
                    contentDescription = "go to FavoriteScreen"
                )
            }
            ScreenDivider(modifier = Modifier.padding(vertical = 10.dp))
            RescuedAnimalList()
        }
    }
}

@Composable
fun RescuedAnimalList(list: List<Animal>) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()) {
        itemsIndexed(list) { index, item ->
            RescuedAnimalItem(index = index, item = item, isSelected = index == viewModel.selectedIndex, onClicked = { i -> viewModel.setSelectedIndex(i) })
            if(index != list.lastIndex) ScreenDivider(modifier = Modifier.padding(horizontal = 20.dp))
        }
    }
}


@Composable
fun RescuedAnimalItem(index: Int, item: Animal, isSelected: Boolean, onClicked: (Int) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClicked(index)
        }
        .padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(modifier = Modifier.weight(1f), text = item)
        if(isSelected) {
            Spacer(modifier = Modifier.width(10.dp))
            VectorIcon(
                vector = Icons.Filled.FavoriteBorder,
                tint = Text_600,
                contentDescription = null
            )
        }
    }
}
