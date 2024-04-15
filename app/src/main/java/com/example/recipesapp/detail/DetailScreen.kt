package com.example.recipesapp.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.recipesapp.api.MainViewModel
import com.example.recipesapp.api.Repository
import com.example.recipesapp.api.ResultState


@Composable
fun DetailScreen(navController: NavController, image: String, tittle: String, des: String) {
    val repository = remember {
        Repository()
    }
    val viewModel = remember {
        MainViewModel(repository)
    }
    var isDetail by remember {
        mutableStateOf(false)
    }
    var detailData by remember {
        mutableStateOf<Detail?>(null)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getDetail(des)
    }
    val detailState by viewModel.allDetail.collectAsState()
    when (detailState) {
        is ResultState.Error -> {
            isDetail = false
            val error = (detailState as ResultState.Error).error
            Text(text = error.toString())
        }

        is ResultState.Loading -> {
            isDetail = true
        }

        is ResultState.Success -> {
            isDetail = false
            val success = (detailState as ResultState.Success).success
            detailData = success
        }
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(start = 1.dp)
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.White.copy(alpha = 0.40f)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                imageVector = Icons.Outlined.ArrowBackIosNew,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.clickable { navController.popBackStack() }

            )
        }


        AsyncImage(
            model = image,
            contentDescription = "",
            modifier = Modifier
                .width(360.dp)
                .height(205.dp),
            contentScale = ContentScale.Crop
        )


        Card(
            modifier = Modifier
                .padding(top = 0.dp)
                .width(414.dp)
                .height(523.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(
                Color(0XFF7B7B7B)
            ),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
        ) {
            Card(
                modifier = Modifier
                    .padding(start = 120.dp)
                    .width(120.dp)
                    .height(6.dp), shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "")
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = tittle.toString(),
                    color = Color.White,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
                detailData?.meals?.let {
                    it.get(0).strInstructions?.let { it1 ->
                        Text(
                            text = it1,
                            color = Color.White
                        )
                    }

                }
            }
        }

    }
}
