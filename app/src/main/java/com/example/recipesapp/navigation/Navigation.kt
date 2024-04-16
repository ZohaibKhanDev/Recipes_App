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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipesapp.Worlds.WorldScreen
import com.example.recipesapp.detail.DetailScreen
import com.example.recipesapp.fav.FavScreen
import com.example.recipesapp.homescreen.HomeScreen
import com.example.recipesapp.worlddetail.WorldDetail

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Canadian.route) {
            WorldScreen(navController)
        }
        composable(Screen.Favourite.route) {
            FavScreen(navController)
        }
        composable(
            Screen.Detail.route + "/{image}/{tittle}/{des}",
            arguments = listOf(

                navArgument("image") {
                    type = NavType.StringType
                },
                navArgument("tittle") {
                    type = NavType.StringType
                },

                navArgument("des") {
                    type = NavType.StringType
                },
                )
        ) {
            val image=it.arguments?.getString("image")!!
            val tittle=it.arguments?.getString("tittle")!!
            val des=it.arguments?.getString("des")!!
            DetailScreen(navController,image,tittle,des)
        }
        composable(Screen.WorldDetail.route + "/{image}/{tittle}/{des}",
            arguments = listOf(

                navArgument("image") {
                    type = NavType.StringType
                },
                navArgument("tittle") {
                    type = NavType.StringType
                },

                navArgument("des") {
                    type = NavType.StringType
                },
            )){
            val image=it.arguments?.getString("image")!!
            val tittle=it.arguments?.getString("tittle")!!
            val des=it.arguments?.getString("des")!!
            WorldDetail(navController,image,tittle,des)
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Entry() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        Navigation(navController = navController)
    }

}






sealed class Screen(
    val tittle: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Home : Screen(
        "Home",
        "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )
    object WorldDetail : Screen(
        "WorldDetail",
        "WorldDetail",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )
    object Canadian :
        Screen(
            "Canadian",
            "Canadian",
            selectedIcon = Icons.Filled.RestaurantMenu,
            unselectedIcon = Icons.Outlined.RestaurantMenu
        )

    object Favourite : Screen(
        "Fav",
        "Fav",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite
    )



    object Detail : Screen(
        "Detail",
        "Detail",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
}

@Composable
fun BottomNavigation(navController: NavController) {
    val item = listOf(
        Screen.Home,
        Screen.Canadian,
        Screen.Favourite,

    )

    NavigationBar(containerColor = Color(0XFF1E1E1E)) {
        val navStack by navController.currentBackStackEntryAsState()
        val current = navStack?.destination?.route
        item.forEach {
            NavigationBarItem(selected = current == it.route, onClick = {
                navController.navigate(it.route) {
                    navController.graph?.let {
                        it.route?.let { it1 -> popUpTo(it1) }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, icon = {
                if (current == it.route) {
                    Icon(
                        imageVector = it.selectedIcon,
                        contentDescription = "",
                        tint = Color.Red
                    )
                } else {
                    Icon(
                        imageVector = it.selectedIcon,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            },
                label = {
                    AnimatedVisibility(visible = current == it.route) {
                        Text(text = it.route, color = Color.Red)
                    }
                }

            )
        }
    }
}



