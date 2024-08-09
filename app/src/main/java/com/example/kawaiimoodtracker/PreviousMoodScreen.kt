package com.example.kawaiimoodtracker

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.kawaiimoodtracker.ui.theme.KawaiiMoodTrackerTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun PreviousMoodScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    moodViewModel: MoodViewModel
) {
    val moodEntries by moodViewModel.moodStateHolder.moodEntries.observeAsState(initial = emptyList())

    val groupedByMonthYearDay = remember(moodEntries) {
        moodEntries.groupBy { entry ->
            Pair(entry.dateTime.year + 1900, entry.dateTime.month + 1)
        }.mapValues { entry ->
            entry.value.groupBy { moodEntry ->
                moodEntry.dateTime.date
            }
        }
    }
    Log.d("GroupedEntries", "Grouped Entries: $groupedByMonthYearDay")

    val currentYear = remember { mutableStateOf(Calendar.getInstance().get(Calendar.YEAR)) }
    val currentMonth = remember { mutableStateOf(Calendar.getInstance().get(Calendar.MONTH) + 1) }

    Log.d("CurrentYearMonth", "Year: ${currentYear.value}, Month: ${currentMonth.value}")

    // Adjust the key to match the corrected grouping
    val currentMonthKey = Pair(currentYear.value, currentMonth.value)
    Log.d("MonthKey", "Current Month Key: $currentMonthKey")

    val currentMonthEntries = groupedByMonthYearDay[currentMonthKey] ?: emptyMap()
    Log.d("CurrentMonthEntries", "Current Month Entries: $currentMonthEntries")


    val selectedmood = moodViewModel.moodStateHolder.selectedMoodIndex


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        //top back to calendar button
        TopBackButton(navController)

        Column (horizontalAlignment = Alignment.CenterHorizontally,){

            //Previous mood recorded quote
            RecordedQuote()

            Spacer(modifier = modifier.height(16.dp))

            //image and text of emotion
            MoodExpression(selectedmood)

            Spacer(modifier = modifier.height(16.dp))

            //Previous time and date of mood
            DateTimePreviousMood(selectedmood)
            
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
fun TopBackButton(navController: NavController, modifier: Modifier = Modifier) {
    Row (modifier = modifier.fillMaxWidth()){
        IconButton(onClick = { navController.navigate("MoodCalendarScreen")}) {
            Icon(
                imageVector = Icons.Sharp.ArrowBack,
                modifier = modifier
                    .size(48.dp),
                contentDescription = "Back to calendar button")
        }
    }
}
@Composable
fun DateTimePreviousMood(selectedMoood: LiveData<MoodEntry>, modifier: Modifier = Modifier) {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val dateString = dateFormat.format(selectedMoood.value?.dateTime ?: "")
    val timeString = timeFormat.format(selectedMoood.value?.dateTime ?: "")

    Text(
        text = dateString,
        fontSize = 20.sp,
        modifier = modifier
            .width(103.dp)
            .height(23.dp),
        textAlign = TextAlign.Center,
    )
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
fun MoodExpression(selectedMoood: LiveData<MoodEntry>, modifier: Modifier = Modifier) {
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
        selectedMoood.value?.let { painterResource(id = it.selectedImagesRes) }?.let {
            Image(
                painter = it,
                modifier = modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium),

                contentDescription = "Expression Image")
        }
    }
    Spacer(modifier = modifier.height(16.dp))
    selectedMoood.value?.let {
        Text(
        text = it.feelingName,
        fontSize = 24.sp,
        modifier = modifier
            .width(187.dp),
        textAlign = TextAlign.Center,
    )
    }
}

@Preview (showBackground = true)
@Composable
private fun PreviousMoodScreenPreview() {
    KawaiiMoodTrackerTheme {
        //PreviousMoodScreen()
    }
}