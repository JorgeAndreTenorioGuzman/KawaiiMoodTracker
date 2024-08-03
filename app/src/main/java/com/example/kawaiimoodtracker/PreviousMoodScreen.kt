package com.example.kawaiimoodtracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.material.icons.sharp.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kawaiimoodtracker.ui.theme.KawaiiMoodTrackerTheme

@Composable
fun PreviousMoodScreen(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        //modifier = modifier.fillMaxSize()
    ) {
        //top back to calendar button
        TopBackButton()

        Column (horizontalAlignment = Alignment.CenterHorizontally,){

            //Previous mood recorded quote
            RecordedQuote()

            Spacer(modifier = modifier.height(16.dp))

            //image and text of emotion
            MoodExpression()

            Spacer(modifier = modifier.height(16.dp))

            //Previous time and date of mood
            DateTimePreviousMood()
            
            Spacer(modifier = modifier.height(16.dp))

            //Previous mood reason
            PreviousMoodReason()

        }
    }

}

@Composable
fun PreviousMoodReason(modifier: Modifier = Modifier) {
    Text(
        text = "I went to watch a movie",
    )
}

@Composable
fun RecordedQuote(modifier: Modifier = Modifier) {
    Text(
        text = "Quote",
        modifier = modifier
            .width(271.dp),
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun TopBackButton(modifier: Modifier = Modifier) {
    Row (modifier =  modifier.fillMaxWidth()){
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Sharp.ArrowBack,
                modifier = modifier
                    .size(48.dp),
                contentDescription = "Back to calendar button")
        }
    }
}
@Composable
fun DateTimePreviousMood(modifier: Modifier = Modifier) {
    Text(
        text = "July 29",
        fontSize = 20.sp,
        modifier = modifier
            .width(103.dp)
            .height(23.dp),
        textAlign = TextAlign.Center,
    )
    Text(
        text = "9:00 pm",
        fontSize = 20.sp,
        modifier = modifier
            .width(103.dp)
            .height(23.dp),
        textAlign = TextAlign.Center,
    )
}
@Composable
fun MoodExpression(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(224.dp)
            .clip(MaterialTheme.shapes.medium)

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            modifier = modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),

            contentDescription = "Expression Image")
    }
    Spacer(modifier = modifier.height(16.dp))
    Text(
        text = "Awesome",
        fontSize = 24.sp,
        modifier = modifier
            .width(187.dp),
        textAlign = TextAlign.Center,
    )
}

@Preview (showBackground = true)
@Composable
private fun PreviousMoodScreenPreview() {
    KawaiiMoodTrackerTheme {
        PreviousMoodScreen()
    }
}