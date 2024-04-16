package com.example.recipesapp.domain.model.worlds

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.recipesapp.R
import com.example.recipesapp.presentation.viewmodel.MainViewModel
import com.example.recipesapp.domain.repository.Repository
import com.example.recipesapp.domain.usecase.ResultState
import com.example.recipesapp.domain.model.favourite.Fav
import com.example.recipesapp.data.local.db.MyDataBase
import com.example.recipesapp.presentation.ui.navigation.Screen
import com.example.recipesapp.timestampToTime

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WorldScreen(navController: NavController) {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context, MyDataBase::class.java, "demo.db"

    ).allowMainThreadQueries().build()
    var textField by remember {
        mutableStateOf("")
    }
    val repository = remember {
        Repository(db)
    }
    val viewModel = remember {
        MainViewModel(repository)
    }
    var isWorld by remember {
        mutableStateOf(false)
    }
    var worldData by remember {
        mutableStateOf<com.example.recipesapp.domain.model.worlds.World?>(null)
    }
    LaunchedEffect(key1 = isWorld) {
        viewModel.getWorld()
    }
    val state by viewModel.allWorld.collectAsState()
    when (state) {
        is ResultState.Error -> {
            isWorld = false
            val error = (state as ResultState.Error).error
            Text(text = error.toString())
        }

        is ResultState.Loading -> {
            isWorld = true
        }

        is ResultState.Success -> {
            isWorld = false
            val success = (state as ResultState.Success).response
            worldData=success
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "WorldRecipe", color = Color.White)
        },
            colors = TopAppBarDefaults.topAppBarColors(Color(0XFF000000)), actions = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color(0XFFFF6B00), modifier = Modifier
                        .padding(end = 10.dp)
                        .width(30.dp)
                        .height(30.dp)
                )
            })
    }) {
       /*if (isWorld){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }*/

           LazyVerticalGrid(
               columns = GridCells.Fixed(2),
               modifier = Modifier.fillMaxWidth().background(Color(0XFF000000)).padding(top = it.calculateTopPadding()),
               verticalArrangement = Arrangement.SpaceBetween,
               horizontalArrangement = Arrangement.Center
           ) {
               worldData?.results?.let { results ->
                   items(results) { fa ->
                       WorldItem(result = fa, navController = navController)
                   }
               }
           }





    }
}

@Composable
fun WorldItem(result: com.example.recipesapp.domain.model.worlds.Result, navController: NavController) {

    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        MyDataBase::class.java,
        "demo.db"

    ).allowMainThreadQueries()
        .build()
    var textField by remember {
        mutableStateOf("")
    }
    val repository = remember {
        Repository(db)
    }
    val viewModel = remember {
        MainViewModel(repository)
    }
    var star by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .clickable { navController.navigate(Screen.WorldDetail.route + "/${Uri.encode(result.thumbnailUrl)}/${result.name}/${result.description}") }
            .padding(10.dp)
            .width(168.dp)
            .height(216.dp)
            .background(Color(0XFF1E1E1E)),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0XFF707070)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = result.thumbnailUrl,
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .width(99.dp)
                    .height(87.dp)
                    .align(Alignment.TopEnd)
            )

            Text(
                text = result.name,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 9.dp, top = 9.dp),
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                color = Color.White
            )
            Text(
                text = "Dinner",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 9.dp, bottom = 35.dp),
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = Color(0XFF2958FF)
            )

            if (star) {
                Icon(imageVector = Icons.Filled.Favorite,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            star = !star
                        }
                        .align(Alignment.TopStart)
                        .padding(top = 9.dp, start = 10.dp),
                    tint = Color(0XFFFF6B00))
            } else {
                Icon(imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            val fav =
                                Fav(null, result.thumbnailUrl, result.name, result.description)
                            viewModel.Insert(fav)
                            star = !star
                        }
                        .align(Alignment.TopStart)
                        .padding(top = 9.dp, start = 10.dp),
                    tint = Color(0XFFFF6B00))
            }

            Image(
                painter = painterResource(id = R.drawable.clock),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 80.dp, start = 4.dp)
                    .width(13.dp)
                    .height(16.dp)
            )

            val date = timestampToTime(System.currentTimeMillis().toLong())
            Text(
                text = date,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 80.dp, start = 20.dp),
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                color = Color(0XFFFF6B00)
            )

            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "",
                tint = Color(0XFFFF6B00),
                modifier = Modifier
                    .align(
                        Alignment.BottomStart
                    )
                    .padding(start = 8.dp, bottom = 5.dp)
            )

            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "",
                tint = Color(0XFFFF6B00),
                modifier = Modifier
                    .align(
                        Alignment.BottomStart
                    )
                    .padding(start = 30.dp, bottom = 5.dp)
            )

            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "",
                tint = Color(0XFFFF6B00),
                modifier = Modifier
                    .align(
                        Alignment.BottomStart
                    )
                    .padding(start = 53.dp, bottom = 5.dp)
            )

            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = "",
                tint = Color(0XFFFF6B00),
                modifier = Modifier
                    .align(
                        Alignment.BottomStart
                    )
                    .padding(start = 75.dp, bottom = 5.dp)
            )

        }
    }

}

