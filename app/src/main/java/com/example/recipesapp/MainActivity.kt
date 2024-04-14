package com.example.recipesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipesapp.api.Indian
import com.example.recipesapp.api.MainViewModel
import com.example.recipesapp.api.Meal
import com.example.recipesapp.api.Repository
import com.example.recipesapp.api.ResultState
import com.example.recipesapp.navigation.Entry
import com.example.recipesapp.ui.theme.RecipesAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesAppTheme {
                Entry()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
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
                            .width(25.dp)
                            .height(26.dp)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding())
                .background(Color(0XFF1E1E1E)),
            color = MaterialTheme.colorScheme.background
        ) {
            if (isIndian) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0XFF1E1E1E)),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                indianData?.meals?.let {indian->
                    items(indian){fav->
                        IndianItem(meal = fav)
                    }
                }
            }
        }
    }
}
@Composable
fun IndianItem(meal: Meal) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(2.dp)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AsyncImage(model = meal.strMealThumb, contentDescription = "")
        }
    }
}
