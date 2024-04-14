package com.example.recipesapp.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.RestaurantMenu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recipesapp.HomeScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Home.route ){
        composable(Screen.Home.route){
            HomeScreen()
        }
        composable(Screen.Canadian.route){
            CanadianScreen()
        }
        composable(Screen.Favourite.route){
            FavScreen()
        }
        composable(Screen.Setting.route){
            SettingScreen()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Entry() {
    val navController= rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        Navigation(navController = navController)
    }
    
}
@Composable
fun CanadianScreen() {
    Text(text = "CandianScreen")
}
@Composable
fun FavScreen() {
    Text(text = "Fav")
}
@Composable
fun SettingScreen() {

    Text(text = "SettingScreen")
}
sealed class Screen(
    val tittle:String,
    val route:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector
){
    object Home: Screen("Home","Home", selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home)
    object Canadian :
        Screen("Canadian","Canadian", selectedIcon = Icons.Filled.RestaurantMenu, unselectedIcon = Icons.Outlined.RestaurantMenu)
    object Favourite: Screen("Fav","Fav", selectedIcon = Icons.Filled.Favorite, unselectedIcon = Icons.Outlined.Favorite)
    object Setting: Screen("Setting","Setting", selectedIcon = Icons.Filled.Settings, unselectedIcon = Icons.Outlined.Settings)
}

@Composable
fun BottomNavigation(navController: NavController) {
    val item= listOf(
        Screen.Home,
        Screen.Canadian,
        Screen.Favourite,
        Screen.Setting
    )

    NavigationBar (containerColor = Color(0XFF1E1E1E)){
        val navStack by navController.currentBackStackEntryAsState()
        val current=navStack?.destination?.route
        item.forEach {
            NavigationBarItem(selected = current==it.route, onClick = {
                navController.navigate(it.route) {
                    navController.graph?.let {
                        it.route?.let { it1 -> popUpTo(it1) }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, icon = { 
                if (current==it.route){
                    Icon(imageVector = it.selectedIcon, contentDescription = "", tint = Color.Red)
                }else{
                    Icon(imageVector = it.selectedIcon, contentDescription = "", tint = Color.White)
                }
            },
                label = {
                    AnimatedVisibility(visible = current==it.route) {
                        Text(text = it.route, color = Color.Red)
                    }
                }
                
                )
        }
    }
}



