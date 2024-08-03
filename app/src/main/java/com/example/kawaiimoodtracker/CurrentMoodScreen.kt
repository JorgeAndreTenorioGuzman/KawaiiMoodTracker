package com.example.kawaiimoodtracker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.material.icons.sharp.Send
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

@Composable
fun CurrentMoodScreen(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 32.dp)
            .fillMaxSize()
    ) {

        //top bar buttons
        TopBarButtons()

        //quote generator
        QuoteGenerator()

        Spacer(modifier = modifier.height(16.dp))

        //image
        ExpressionImage()

        Spacer(modifier = modifier.height(16.dp))

       //date and time
        DateTimeMood()
        
        Spacer(modifier = modifier.height(16.dp))

        RecordReason()

        Spacer(modifier = modifier.height(16.dp))

    }

}

@Composable
fun DateTimeMood(modifier: Modifier = Modifier) {
    Text(
        text = "July 29",
        fontSize = 20.sp,
        modifier = modifier
            .width(103.dp),
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = modifier.height(8.dp))
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
fun TopBarButtons(modifier: Modifier = Modifier) {
    Row (modifier =  modifier.fillMaxWidth()){
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Sharp.DateRange,
                modifier = modifier
                    .size(48.dp),
                contentDescription = "Mood Calendar Button")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/}) {
            Icon(
                imageVector = Icons.Sharp.AddCircle,
                modifier = modifier
                    .size(48.dp),
                contentDescription = "Add mood Button" )
        }
    }
}

@Composable
fun QuoteGenerator(modifier: Modifier = Modifier) {
    Row (modifier =  modifier.width(230.dp)) {
        IconButton(
            onClick = { /*TODO*/ },


            ) {
            Icon(
                imageVector = Icons.Sharp.Refresh,
                contentDescription = "Generate quote"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ },

            ) {
            Icon(
                imageVector = Icons.Sharp.Close,
                contentDescription = "Generate quote"
            )
        }
    }
    Text(
        text = "Generate quote",
        modifier = modifier
            .width(271.dp),
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun ExpressionImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(224.dp)
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = 1.dp,
                color = Color(0xFFA0A0A0),
                shape = MaterialTheme.shapes.medium
            ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.awesome_expression),
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
@Composable
fun RecordReason(modifier: Modifier = Modifier) {

    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = "",
            label = { Text(
                text = "Add reason",
                fontSize = 13.sp,
                color = Color(0xFF939393),

            )},
            onValueChange = {},
            modifier = Modifier
                .width(212.dp)
                .height(41.dp)
                .border(width = 1.dp, shape = MaterialTheme.shapes.medium, color = Color(0xFFA0A0A0)),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color(0xFFFDBED4)

            ),
        )
        Spacer(modifier = modifier.width(4.dp))
        IconButton(
            onClick = { /*TODO*/},
            modifier = Modifier
                .size(41.dp)
                .padding(0.dp),
            /*shape = MaterialTheme.shapes.medium,*/

        ) {
            Icon(
                imageVector = Icons.Sharp.Send,
                contentDescription = "send",
                tint = Color(0xFFFDBED4),
                modifier = Modifier
                    .size(24.dp),
            )
        }
    }
}


@Composable
fun AddedReason(modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "I watched a movie")
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Sharp.Edit,
                contentDescription = "Edit reason"
            )
        }
    }
}


@Preview (showBackground = true)
@Composable
private fun AddedReasonPreview() {
    AddedReason()
}

@Preview
@Composable
private fun RecordReasonPreview() {
    RecordReason()
}

@Preview(showBackground = true)
@Composable
private fun CurrentMoodScreenPreview() {
    CurrentMoodScreen()
}