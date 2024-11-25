package com.android.test.mangareader.ui.view.mangaDetails

import android.view.RoundedCorner
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.android.test.mangareader.R

@Composable
fun MangaInfo(
    imageUrl: String,
    title: String,
    author: String,
    rating: Double,
    chapters: Int,
    language: String,
    description: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            MangaImage(imageUrl = "https://static0.gamerantimages.com/wordpress/wp-content/uploads/2022/06/kaguya-sama-cast.jpg")

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "By $author", fontSize = 16.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceAround
            ){
                Chip("Manga")
                Chip("Characters")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8E44AD))
                ){
                    Text(text = "Read Now", color = Color.White)
                }
            }


        }
    }
}

@Composable
fun MangaImage(imageUrl: String) {
    AsyncImage(model = imageUrl, contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp)),
        contentScale = ContentScale.Crop)
}

@Composable
fun Chip(label: String){
    Box(
        modifier = Modifier
            .background(Color(0xFFDDDDDD), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ){
        Text(text = label, fontSize = 14.sp, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    MangaInfo(
        imageUrl = "",
        title = "Kaguya-Sama: Love is War",
        author = "Aka Akasaka",
        rating = 8.0,
        chapters = 255,
        language = "English",
        description = "Kaguya-sama: Love Is War (Japanese: かぐや様は告らせたい ～天才たちの恋愛頭脳戦～, Hepburn: Kaguya-sama wa Kokurasetai: Tensai-tachi no Ren'ai Zunōsen, lit. 'Lady Kaguya Wants to Make Him Confess: The Geniuses' War of Hearts and Minds') is a Japanese romantic comedy manga series written and illustrated by Aka Akasaka. It was first serialized in Shueisha's seinen manga magazine Miracle Jump from May 2015 to January 2016, and later transferred to Weekly Young Jump, where it ran from March 2016 to November 2022. Its chapters were collected in 28 tankōbon volumes. In North America, the manga is licensed in English by Viz Media."
    )
}
