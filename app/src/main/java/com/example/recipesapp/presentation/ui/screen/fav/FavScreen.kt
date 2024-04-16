package com.example.recipesapp.presentation.ui.screen.fav

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipesapp.R
import com.example.recipesapp.domain.model.favourite.Fav
import com.example.recipesapp.domain.usecase.ResultState
import com.example.recipesapp.presentation.ui.navigation.Screen
import com.example.recipesapp.presentation.viewmodel.MainViewModel
import com.example.recipesapp.timestampToTimes
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavScreen(navController: NavController) {
    val viewModel : MainViewModel = koinInject()
    var textField by remember {
        mutableStateOf("")
    }

    var isFav by remember {
        mutableStateOf(false)
    }

    var favData by remember {
        mutableStateOf<List<Fav>?>(null)
    }
    LaunchedEffect(key1 = isFav) {
        viewModel.getAllFav()
    }
    val state by viewModel.allFav.collectAsState()
    when (state) {
        is ResultState.Error -> {
            isFav = false
            val error = (state as ResultState.Error).error
            Text(text = error.toString())
        }

        is ResultState.Loading -> {
            isFav = true
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ResultState.Success -> {
            isFav = false
            val success = (state as ResultState.Success).response
            favData = success
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Favourite", color = Color.White)
            }, colors = TopAppBarDefaults.topAppBarColors(Color(0XFF373737)),
                actions = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color(0XFFFF6B00),
                        modifier = Modifier
                            .padding(end = 11.dp)
                            .width(30.dp)
                            .height(30.dp)
                    )
                })
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .background(Color(0XFF373737)),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            favData?.let { favs ->
                items(favs) { favt ->
                    FavItem(fav = favt, navController = navController)
                }

            }
        }
    }
}

@Composable
fun FavItem(fav: Fav, navController: NavController) {
    var like by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .clickable { navController.navigate(Screen.Detail.route + "/${Uri.encode(fav.image)}/${fav.tittle}/${fav.des}") }
        .padding(10.dp)
        .width(344.dp)
        .height(64.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0XFF373737))) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0XFF373737))
                .padding(top = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = fav.image,
                    contentDescription = "",
                    modifier = Modifier
                        .width(66.dp)
                        .height(54.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = fav.tittle,
                        color = Color.White,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold
                    )

                    Row {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "",
                            tint = Color(0XFFFF6B00)
                        )
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "",
                            tint = Color(0XFFFF6B00)
                        )
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "",
                            tint = Color(0XFFFF6B00)
                        )
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = "",
                            tint = Color(0XFFFF6B00)
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(end = 5.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {if (like){
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "",
                        modifier = Modifier.clickable { like=!like},
                        tint = Color(0XFFFF6B00)
                    )
                }else{
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "",
                        modifier = Modifier.clickable {like=!like },
                        tint = Color(0XFFFF6B00)
                    )
                }


                    val date = timestampToTimes(System.currentTimeMillis())
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.clock),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(13.dp)
                                .height(16.dp)
                        )
                        Text(
                            text = date,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            fontWeight = FontWeight.Medium,
                            color = Color(0XFFFF6B00)
                        )
                    }

                }

            }
        }
    }
}