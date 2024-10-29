package com.example.rescuedanimals.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rescuedanimals.domain.entity.Animal
import com.example.rescuedanimals.ui.theme.Diary_Green_400
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.StateFlow


@Composable
fun AnimalList(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    itemListState: StateFlow<List<Animal>>,
    onLoadMore: (Boolean) -> Unit,
    itemClicked: (Int, Animal) -> Unit
) {
    val list by itemListState.collectAsStateWithLifecycle()

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.firstVisibleItemIndex
        }.collect { visibleIndex ->
            if (visibleIndex == listState.layoutInfo.totalItemsCount - 20 && listState.lastScrolledForward) {
                onLoadMore(false)
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        itemsIndexed(list) { index, item ->
            AnimalItemCompact(
                index = index,
                item = item,
                onClicked = { index, animal -> itemClicked(index, animal) })
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}


@Composable
fun AnimalItemCompact(index: Int, item: Animal, onClicked: (Int, Animal) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClicked(index, item)
            }
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Row {
            GlideImage(
                imageModel = { item.filename }, // loading a network image using an URL.
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                loading = { LoadingIcon() },
                failure = {
                    PlaceHolderIcon()
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    text = "# ${item.desertionNo}"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    text = "Name : ${item.kindCd}"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    text = "Age : ${item.age}"
                )
            }
        }
    }
}


@Composable
fun AnimalItemMedium(index: Int, item: Animal, onClicked: (Int, Animal) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClicked(index, item)
            }
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            GlideImage(
                imageModel = { item.popfile }, // loading a network image using an URL.
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                loading = { LoadingIcon() },
                failure = {
                    PlaceHolderIcon()
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.padding(start = 10.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                text = "# ${item.desertionNo}"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.padding(start = 10.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                text = "Name : ${item.kindCd}"
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.padding(start = 10.dp),
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                text = "Age : ${item.age}"
            )
        }
    }
}

@Composable
fun AnimalItemExpanded(index: Int, item: Animal, onClicked: (Int, Animal) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClicked(index, item)
            }
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Row {
            GlideImage(
                imageModel = { item.popfile }, // loading a network image using an URL.
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                loading = { LoadingIcon() },
                failure = {
                    PlaceHolderIcon()
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    text = "# ${item.desertionNo}"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    text = "Name : ${item.kindCd}"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    text = "Age : ${item.age}"
                )
            }
        }
    }
}