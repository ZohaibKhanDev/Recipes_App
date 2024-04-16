package com.example.recipesapp.worlddetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.RoomService
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun WorldDetail(navController: NavController, image: String, tittle: String, des: String) {
    val scrollState= rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()

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
                    .verticalScroll(scrollState)
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.WatchLater,
                        contentDescription = "",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .padding(start = 20.dp)

                            .width(18.dp)
                            .height(18.dp)
                    )

                    Text(text = "10 mins", fontSize = MaterialTheme.typography.labelMedium.fontSize)

                    Icon(
                        imageVector = Icons.Outlined.RoomService,
                        contentDescription = "",
                        tint = Color.LightGray, modifier = Modifier
                            .width(20.dp)
                    )

                    Text(text = "1 Serving", fontSize = MaterialTheme.typography.labelMedium.fontSize)
                }
                Divider(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .width(300.dp)
                        .height(2.dp), color = Color(0XFF474747)
                )
                Text(text = des)


            }
        }

    }
    
}