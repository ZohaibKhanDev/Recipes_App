package com.example.recipesapp

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.recipesapp.api.MainViewModel
import com.example.recipesapp.api.Meal
import com.example.recipesapp.api.Repository
import com.example.recipesapp.canadian.MealX
import com.example.recipesapp.database.Fav
import com.example.recipesapp.database.MyDataBase
import com.example.recipesapp.navigation.Entry
import com.example.recipesapp.navigation.Screen
import com.example.recipesapp.ui.theme.RecipesAppTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            RecipesAppTheme {
                Entry()
            }
        }
    }
}

@Composable
fun CanadianItem(meal: MealX, navController: NavController, isCanadian: Boolean) {
    var like by remember {
        mutableStateOf(false)
    }
    if (isCanadian){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }
    else{
        Card(modifier = Modifier
            .clickable { navController.navigate(Screen.Detail.route + "/${Uri.encode(meal.strMealThumb)}/${meal.strMeal}/${meal.idMeal}") }
            .padding(5.dp)
            .width(344.dp)
            .height(64.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0XFF373737))) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                        Icon(
                            imageVector = if (like) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "",
                            modifier = Modifier.clickable { like = !like },
                            tint = Color(0XFFFF6B00)
                        )

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
}

fun timestampToTimes(timestamp: Long): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val instant = Instant.ofEpochMilli(timestamp)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return localDateTime.format(formatter)
    } else {

        return "No data Found"
    }
}

@Composable
fun IndianItem(meal: Meal, isIndian: Boolean, navController: NavController) {
    val context= LocalContext.current
    val db= Room.databaseBuilder(
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
    if (isIndian){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

        }
    }else{
        Card(
            modifier = Modifier
                .clickable { navController.navigate(Screen.Detail.route + "/${Uri.encode(meal.strMealThumb)}/${meal.strMeal}/${meal.idMeal}") }
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
                    model = meal.strMealThumb,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(5.dp)
                        .width(99.dp)
                        .height(87.dp)
                        .align(Alignment.TopEnd)
                )

                Text(
                    text = meal.strMeal,
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
                        .padding(start = 9.dp, bottom = 23.dp),
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
                                val fav = Fav(null, meal.strMealThumb, meal.strMeal, meal.idMeal)
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

}

fun timestampToTime(timestamp: Long): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val instant = Instant.ofEpochMilli(timestamp)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return localDateTime.format(formatter)
    } else {

        return "No data Found"
    }
}
