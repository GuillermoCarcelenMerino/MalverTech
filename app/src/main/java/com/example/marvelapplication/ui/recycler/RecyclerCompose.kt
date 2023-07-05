package com.example.marvelapplication.ui.recycler

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.ui.BasicFragment

@Composable
fun generateRecycler(contentList: LazyPagingItems<MarvelCharacter>, basicFragment: BasicFragment) {
    if (contentList.loadState.append is LoadState.Error) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            title = {
                Text(text = "Error in request")
            },
            buttons = {},
        )
    } else {
        LazyColumn {
            items(
                count = contentList.itemCount,
            ) {
                val item = contentList[it]
                if (item != null) {
                    generateItemLayout(item, basicFragment)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun generateItemLayout(marvelCharacter: MarvelCharacter, fragment: BasicFragment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(Color.Blue)
            .padding(20.dp),
        onClick = { fragment.goToDetails(marvelCharacter) },
    ) {
        Row(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = getImage(marvelCharacter),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(5.dp),
            )
            Spacer(
                Modifier
                    .weight(1f),
            )

            Column(
                Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .padding(5.dp),
            ) {
                Text(text = marvelCharacter.name ?: "no name given")
                Spacer(
                    Modifier
                        .weight(1f)
                        .padding(top = 20.dp),
                )
                Text(
                    text = marvelCharacter.description ?: "No description attached",
                    maxLines = 5,
                    modifier = Modifier.padding(bottom = 10.dp),
                )
            }
        }
    }
}

fun getImage(content: MarvelCharacter) =
    content.thumbnail?.path + "." + content.thumbnail?.extension
