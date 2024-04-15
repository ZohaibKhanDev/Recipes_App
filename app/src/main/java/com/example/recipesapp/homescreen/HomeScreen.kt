package com.example.recipesapp.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipesapp.IndianItem
import com.example.recipesapp.R
import com.example.recipesapp.api.Indian
import com.example.recipesapp.api.MainViewModel
import com.example.recipesapp.api.Repository
import com.example.recipesapp.api.ResultState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var textField by remember {
        mutableStateOf("")
    }
    val repository = remember {
        Repository()
    }
    val viewModel = remember {
        MainViewModel(repository)
    }
    var indianData by remember {
        mutableStateOf<Indian?>(null)
    }
    var isIndian by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isIndian) {
        viewModel.getCountry()
    }

    val state by viewModel.allCountry.collectAsState()
    when (state) {
        is ResultState.Error -> {
            isIndian = false
            val error = (state as ResultState.Error).error
            Text(text = error.toString())
        }

        is ResultState.Loading -> {
            isIndian = true
        }

        is ResultState.Success -> {
            isIndian = false
            val success = (state as ResultState.Success).success
            indianData = success
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {

            }, colors = TopAppBarDefaults.topAppBarColors(Color(0XFF1E1E1E)),
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.navigationicon),
                        contentDescription = "",
                        contentScale = ContentScale.Crop, modifier = Modifier
                            .padding(20.dp)
                            .width(25.dp)
                            .height(26.dp)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "",
                        tint = Color.White, modifier = Modifier
                            .padding(end = 20.dp)
                            .width(30.dp)
                            .height(35.dp)
                    )
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .background(Color(0XFF1E1E1E)),
        ) {


            LazyRow(
                modifier = Modifier
                    .background(Color(0XFF1E1E1E)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                indianData?.meals?.let { indian ->
                    items(indian) { fav ->
                        IndianItem(meal = fav, isIndian)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Welcome",
                    color = Color.White,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Denny",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = Color(0XFFFF6B00)
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 28.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "What would you like\n" +

                            "to cook today?",
                    color = Color(0XFFFF6B00),
                    lineHeight = 36.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            OutlinedTextField(value = textField, onValueChange = {
                textField=it
            })
        }
    }
}