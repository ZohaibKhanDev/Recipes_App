package com.example.recipesapp.homescreen

import Canadian
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.recipesapp.CanadianItem
import com.example.recipesapp.IndianItem
import com.example.recipesapp.R
import com.example.recipesapp.api.Indian
import com.example.recipesapp.api.MainViewModel
import com.example.recipesapp.api.Repository
import com.example.recipesapp.api.ResultState
import com.example.recipesapp.database.Fav
import com.example.recipesapp.database.MyDataBase
import com.example.recipesapp.navigation.Screen
import com.example.recipesapp.search.Meal
import com.example.recipesapp.search.Search
import com.example.recipesapp.timestampToTimes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

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
    var indianData by remember {
        mutableStateOf<Indian?>(null)
    }
    var isIndian by remember {
        mutableStateOf(false)
    }
    var canadianData by remember {
        mutableStateOf<Canadian?>(null)
    }
    var isCanadian by remember {
        mutableStateOf(false)
    }
    var isSearch by remember {
        mutableStateOf(false)
    }

    var searchIcon by remember {
        mutableStateOf(false)
    }
    var searchData by remember {
        mutableStateOf<Search?>(null)
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getCountry()
        viewModel.getCanadian()
        viewModel.getSearch(textField)
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
            val success = (state as ResultState.Success).response
            indianData = success
        }
    }

    val canadianState by viewModel.allCanadian.collectAsState()
    when (canadianState) {
        is ResultState.Error -> {
            isCanadian = false
            val error = (canadianState as ResultState.Error).error
            Text(text = error.toString())
        }

        is ResultState.Loading -> {
            isCanadian = true
        }

        is ResultState.Success -> {
            isCanadian = false
            val success = (canadianState as ResultState.Success).response
            canadianData = success
        }
    }

    val searchState by viewModel.allSearch.collectAsState()
    when (searchState) {
        is ResultState.Error -> {
            isSearch = false
            val error = (searchState as ResultState.Error).error
            Text(text = error.toString())
        }

        is ResultState.Loading -> {
            isSearch = true

        }

        is ResultState.Success -> {
            isSearch = false
            val success = (searchState as ResultState.Success).response
            searchData = success
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = {
        }, colors = TopAppBarDefaults.topAppBarColors(Color(0XFF1E1E1E)), navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.navigationicon),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(20.dp)
                    .width(25.dp)
                    .height(26.dp)
            )
        }, actions = {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .width(30.dp)
                    .height(35.dp)
            )
        })
    }) {
        if(isSearch){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                CircularProgressIndicator()
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .background(Color(0XFF1E1E1E)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp, start = 20.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {
                    OutlinedTextField(
                        value = textField,
                        onValueChange = {
                            textField = it
                        },
                        modifier = Modifier.background(Color(0XFF1E1E1E)),
                        placeholder = {
                            Text(
                                text = "Search Recipe",
                                color = Color.White,
                                fontSize = MaterialTheme.typography.labelSmall.fontSize
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedPlaceholderColor = Color.White,
                            unfocusedPlaceholderColor = Color.White,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            containerColor = Color(0XFF1E1E1E),
                            cursorColor = Color.White,
                            unfocusedIndicatorColor = Color.White,
                            focusedIndicatorColor = Color.White

                        ),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "",
                                modifier = Modifier
                                    .clickable { searchIcon = true }
                                    .width(23.dp)
                                    .height(20.dp),
                                tint = Color.White
                            )
                        },

                        )
                    Icon(
                        imageVector = Icons.Outlined.FilterAlt,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 5.dp)
                            .width(45.dp)
                            .height(45.dp)
                    )


                }
                if (searchData != null && searchIcon) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1000.dp)
                            .padding(top = 20.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        searchData?.meals?.let { search ->
                            items(search) { ml ->
                                searchItem(meal = ml, navController = navController)
                            }
                        }
                    }

                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 1.dp, top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Today’s Fresh Recipe",
                            color = Color.White,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "See All",
                            color = Color(0XFFFF6B00),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,

                            )

                    }
                    LazyRow(
                        modifier = Modifier.background(Color(0XFF1E1E1E)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        indianData?.meals?.let { indian ->
                            items(indian) { fav ->
                                IndianItem(meal = fav, isIndian, navController)
                            }
                        }
                    }




                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Recommended",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold, color = Color.White
                        )

                        Text(
                            text = "See All",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.ExtraBold, color = Color(0XFFFF6B00)
                        )
                    }
                    LazyColumn(
                        modifier = Modifier
                            .padding(bottom = 55.dp)
                            .fillMaxWidth()
                            .height(1000.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        canadianData?.meals?.let { can ->
                            items(can) { hm ->
                                CanadianItem(meal = hm, navController = navController,isCanadian)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun searchItem(meal: Meal, navController: NavController) {
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
    var like by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .clickable { navController.navigate(Screen.Detail.route + "/${Uri.encode(meal.strMealThumb)}/${meal.strMeal}/${meal.idMeal}") }
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
                    model = meal.strMealThumb,
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
                        text = meal.strMeal,
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
                ) {
                    if (like) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                like = !like
                                val fav =
                                    Fav(null, meal.strMealThumb, meal.strMeal, meal.strInstructions)
                                viewModel.Insert(fav)
                            },
                            tint = Color(0XFFFF6B00)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "",
                            modifier = Modifier.clickable { like = !like },
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