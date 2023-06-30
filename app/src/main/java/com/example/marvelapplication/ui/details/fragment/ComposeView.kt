package com.example.marvelapplication.ui.details.fragment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.model.comics.MarvelComic
import com.example.marvelapplication.model.events.MarvelEvent

private var marvelCharacter: MarvelCharacter? = null
private var marvelComic: MarvelComic? = null
private var marvelEvent: MarvelEvent? = null

@Composable
fun addView(item: GenericAnswer, type: Int) {
    setItem(item, type)
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = getNametype(type),
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                )
                AsyncImage(
                    model = getImage(type),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .padding(top = 20.dp),
                )

                Text(
                    text = getDescription(type),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 10.dp),
                    style = TextStyle(fontStyle = FontStyle.Normal),
                )
                if (type == 2) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Start",
                            fontSize = 20.sp,
                            style = TextStyle(fontStyle = FontStyle.Normal),
                        )
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                        )
                        Text(
                            text = getStart(type),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 10.dp),
                            style = TextStyle(fontStyle = FontStyle.Normal),
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 10.dp),
                    ) {
                        Text(
                            text = "End",
                            fontSize = 20.sp,
                            style = TextStyle(fontStyle = FontStyle.Normal),
                        )
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                        )
                        Text(
                            text = getEnd(type),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 10.dp),
                            style = TextStyle(fontStyle = FontStyle.Normal),
                        )
                    }
                }
            }
        }
    }
}

fun getImage(type: Int) = when (type) {
    0 -> marvelCharacter?.thumbnail?.path + "." + marvelCharacter?.thumbnail?.extension
    1 -> marvelComic?.thumbnail?.path + "." + marvelComic?.thumbnail?.extension
    2 -> marvelEvent?.thumbnail?.path + "." + marvelEvent?.thumbnail?.extension
    else -> "Default IMAGE HERE"
}

fun getNametype(type: Int): String = when (type) {
    0 -> marvelCharacter?.name ?: "Empty Name"
    1 -> marvelComic?.name ?: "Empty Name"
    2 -> marvelEvent?.name ?: "Empty Name"
    else -> "Name not found"
}

fun getStart(type: Int): String = marvelEvent?.start ?: "No start assigned"

fun getEnd(type: Int): String = marvelEvent?.end ?: "No end assigned"

fun getDescription(type: Int): String = when (type) {
    0 -> marvelCharacter?.description ?: "No description attached"
    1 -> marvelComic?.description ?: "No description attached"
    2 -> marvelEvent?.description ?: "No description attached"
    else -> "No description attached"
}

fun setItem(item: GenericAnswer, type: Int) {
    when (type) {
        0 -> marvelCharacter = item as MarvelCharacter
        1 -> marvelComic = item as MarvelComic
        2 -> marvelEvent = item as MarvelEvent
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun preview() {
}
