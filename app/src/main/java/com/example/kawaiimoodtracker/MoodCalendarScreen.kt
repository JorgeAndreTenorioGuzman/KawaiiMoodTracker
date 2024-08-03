package com.example.kawaiimoodtracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.ArrowForward
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MoodCalendarScreen(modifier: Modifier = Modifier) {

    Column() {
        TopCloseButton(modifier = modifier.align(Alignment.End))
        ScrollItemsBar()
        RecordedMonthMoods()
    }


}

@Composable
fun TopCloseButton(modifier: Modifier = Modifier) {
    IconButton(onClick = { /*TODO*/ },modifier = modifier) {
        Icon(
            imageVector = Icons.Sharp.Close,
            modifier = modifier.size(48.dp),
            contentDescription = ""
        )
    }
}
@Composable
fun RecordedMonthMoods(modifier: Modifier = Modifier) {
    LazyColumn {
        items(10){index ->
            Spacer(modifier = modifier.height(24.dp))
            DayRecordedMoods()
        }
    }
}

@Composable
fun ScrollItemsBar(modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { /*TODO*/ }, enabled = true) {
            Icon(
                imageVector = Icons.Sharp.ArrowBack,
                contentDescription = ""
            )
        }
        Text(
            text = "July 2024",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        IconButton(onClick = { /*TODO*/ }, enabled = false) {
            Icon(
                imageVector = Icons.Sharp.ArrowForward  ,
                contentDescription = "")
        }

    }
}

@Composable
fun DayRecordedMoods(modifier: Modifier = Modifier) {

    Column {
        Text(
            text = "July 29",
            fontSize = 20.sp,
            modifier = modifier.padding(start = 16.dp)
            )
        Spacer(modifier = modifier.height(16.dp))
        LazyRow (modifier = modifier.fillMaxWidth()){
            items(10){index ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        modifier = modifier
                            .padding(4.dp)
                            .clip(MaterialTheme.shapes.medium),
                        contentDescription = ""
                    )
                   Text(text = "Awesome")
                }
            }
        }
    }

}

@Preview (showBackground = true)
@Composable
private fun DayRecordedMoodsPreview() {
    DayRecordedMoods()
}

@Preview (showBackground = true)
@Composable
private fun ScrollItemsBarPreview() {
    ScrollItemsBar()
}

@Preview (showBackground = true)
@Composable
private fun MoodCalendarScreenPreview() {
    MoodCalendarScreen()
}