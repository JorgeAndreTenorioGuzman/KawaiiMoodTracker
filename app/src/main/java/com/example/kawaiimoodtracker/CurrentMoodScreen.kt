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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CurrentMoodScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    moodViewModel: MoodViewModel
) {

    val moodStateHolder = moodViewModel.moodStateHolder
    val moodEntries by moodStateHolder.moodEntries.observeAsState(emptyList())
    val reasonText by moodStateHolder.reasonText.observeAsState("")
    val reasonSubmittedText by moodStateHolder.reasonSubmittedText.observeAsState("")
    val quote by moodStateHolder.quote.observeAsState("")
    val author by moodStateHolder.author.observeAsState("")

    val mostRecentMood = moodEntries.lastOrNull()


    LaunchedEffect(moodEntries) {
        println("Mood entries updated: $moodEntries")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 32.dp)
            .fillMaxSize()
    ) {

        //top bar buttons
        TopBarButtons(navController = navController)

        //quote generator
        if (mostRecentMood != null) {
            QuoteGenerator(
                quote = quote,
                onCLickGenerateQuote = {moodViewModel.fetchQuoteAndUpdateMoodEntry(mostRecentMood)},
                onCLickClose = {moodStateHolder.clearQuote(); mostRecentMood.quote = ""; mostRecentMood.author = ""}
                )
        }

        Spacer(modifier = modifier.height(16.dp))

        //image
        if (mostRecentMood != null) {
            ExpressionImage(mostRecentMood)
        }

        Spacer(modifier = modifier.height(16.dp))

       //date and time
        if (mostRecentMood != null) {
            DateTimeMood(mostRecentMood)
        }
        
        Spacer(modifier = modifier.height(16.dp))

        RecordReason(
            reasonText = reasonText,
            onValueChange = {moodStateHolder.setReasonText(it)},
            reasonSubmitted = moodStateHolder.reasonSubmitted,
            reasonSubmittedText = reasonSubmittedText,
            onReasonSubmitted = {
                moodStateHolder.setReasonSubmittedText(reasonText)
                moodStateHolder.reasonSubmitted = true
                if (mostRecentMood != null) {
                    mostRecentMood.reason = reasonSubmittedText
                }
            },
            onEditReasonText = {moodStateHolder.reasonSubmitted = false}
        )

        Spacer(modifier = modifier.height(16.dp))

    }

}

@Composable
fun DateTimeMood(mustRecentMood: MoodEntry,modifier: Modifier = Modifier) {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val dateString = dateFormat.format(mustRecentMood.dateTime)
    val timeString = timeFormat.format(mustRecentMood.dateTime)

    Text(
        text = dateString,
        fontSize = 20.sp,
        modifier = modifier
            .width(103.dp),
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = modifier.height(8.dp))
    Text(
        text = timeString,
        fontSize = 20.sp,
        modifier = modifier
            .width(103.dp)
            .height(23.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun TopBarButtons(navController: NavHostController, modifier: Modifier = Modifier) {
    Row (modifier =  modifier.fillMaxWidth()){
        IconButton(
            onClick = { navController.navigate("MoodCalendarScreen")},
        ) {
            Icon(
                imageVector = Icons.Sharp.DateRange,
                modifier = modifier
                    .size(48.dp),
                contentDescription = "Mood Calendar Button")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {navController.navigate("AddMoodScreen")},
        ) {
            Icon(
                imageVector = Icons.Sharp.AddCircle,
                modifier = modifier
                    .size(48.dp),
                contentDescription = "Add mood Button" )
        }
    }
}

@Composable
fun QuoteGenerator(quote: String, onCLickGenerateQuote: () -> Unit,onCLickClose: () -> Unit, modifier: Modifier = Modifier) {
    Row (modifier =  modifier.width(230.dp)) {
        IconButton(
            onClick = onCLickGenerateQuote,


            ) {
            Icon(
                imageVector = Icons.Sharp.Refresh,
                contentDescription = "Generate quote"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onCLickClose,

            ) {
            Icon(
                imageVector = Icons.Sharp.Close,
                contentDescription = "cancel quote"
            )
        }
    }
    Text(
        text = quote,
        modifier = modifier
            .width(271.dp),
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun ExpressionImage(mustRecentMood: MoodEntry, modifier: Modifier = Modifier) {
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
            painter = painterResource(id = mustRecentMood.selectedImagesRes),
            modifier = modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),
            contentDescription = "Expression Image")
    }

    Spacer(modifier = modifier.height(16.dp))

    Text(
        text = mustRecentMood.feelingName,
        fontSize = 24.sp,
        modifier = modifier
            .width(187.dp),
        textAlign = TextAlign.Center,
    )
}
@Composable
fun RecordReason(
    reasonText: String,
    onValueChange: (String) -> Unit,
    reasonSubmitted: Boolean,
    onReasonSubmitted: () -> Unit,
    reasonSubmittedText: String,
    modifier: Modifier = Modifier,
    onEditReasonText: () -> Unit
) {

        if (reasonSubmitted) {
            Row (
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = reasonSubmittedText,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .width(212.dp)
                )
                IconButton(
                    onClick = onEditReasonText,
                    modifier = modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Sharp.Edit,
                        contentDescription = "Edit reason",
                    )
                }
            }
        } else {
            Row (
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

            TextField(
                value = reasonText,
                onValueChange = onValueChange,
                label = { Text(
                    text = "Add reason",
                    fontSize = 13.sp,
                    color = Color(0xFF939393),

                    )},
                modifier = Modifier
                    .width(212.dp)
                    .border(
                        width = 1.dp,
                        shape = MaterialTheme.shapes.medium,
                        color = Color(0xFFA0A0A0)
                    ),
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF9F4666),
                    focusedTextColor = Color(0xFF9F4666),
                    focusedContainerColor = Color(0xFFFFE1EC),
                    unfocusedTextColor = Color(0xFFFFE1EC)
                ),
            )

            Spacer(modifier = modifier.width(4.dp))
            IconButton(
                onClick = onReasonSubmitted,
                modifier = Modifier
                    .size(48.dp)
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

}


// TODO: erase this or use the new one i used inside the RecordReason composable
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
   // RecordReason()
}

@Preview(showBackground = true)
@Composable
private fun CurrentMoodScreenPreview() {
   // CurrentMoodScreen()
}