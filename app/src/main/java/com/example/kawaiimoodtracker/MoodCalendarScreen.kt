package com.example.kawaiimoodtracker

import android.security.identity.CredentialDataResult.Entries
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.ArrowForward
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun MoodCalendarScreen(
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


    Column() {
        TopCloseButton(navController, modifier = modifier.align(Alignment.End))
        ScrollMonthsBar(
            currentYear = currentYear,
            currentMonth = currentMonth,
            onMonthChange = { year, month ->
                currentYear.value = year
                currentMonth.value = month
            }
        )

            RecordedMonthMoods(
                navController = navController,
                moodEntries = currentMonthEntries,
                currentYear = currentYear,
                currentMonth = currentMonth,
                setSelectedMoodIndex = { mood -> moodViewModel.moodStateHolder.setSelectedMoodIndex(mood)}
            )
        }
    }





@Composable
fun TopCloseButton(navController: NavController, modifier: Modifier = Modifier) {
    IconButton(
        onClick = { navController.navigate("CurrentMoodScreen")},
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Sharp.Close,
            modifier = modifier.size(48.dp),
            contentDescription = ""
        )
    }
}

@Composable
fun RecordedMonthMoods(
    navController: NavController,
    modifier: Modifier = Modifier,
    moodEntries: Map<Int, List<MoodEntry>>,
    currentYear: MutableState<Int>,
    currentMonth: MutableState<Int>,
    setSelectedMoodIndex: (MoodEntry) -> Unit
) {

    LazyColumn {
        items(moodEntries.keys.sorted()) { day ->
            Spacer(modifier = modifier.height(24.dp))
            DayRecordedMoods(
                navController = navController,
                day = day,
                moods = moodEntries[day] ?: emptyList(),
                setSelectedMoodIndex = setSelectedMoodIndex
            )
        }
    }
}

@Composable
fun ScrollMonthsBar(
    modifier: Modifier = Modifier,
    currentYear: MutableState<Int>,
    currentMonth: MutableState<Int>,
    onMonthChange: (Int, Int) -> Unit,

    ) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = {
                if (currentMonth.value == 1) {
                    currentMonth.value = 12
                    currentYear.value -= 1
                } else {
                    currentMonth.value -= 1
                }
                onMonthChange(currentYear.value, currentMonth.value)
            },
            enabled = true
        ) {
            Icon(
                imageVector = Icons.Sharp.ArrowBack,
                contentDescription = "Go to Previous Month"
            )
        }
        Text(
            text = "${getMonthName(currentMonth.value)} ${currentYear.value}",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        IconButton(
            onClick = {
            if (currentMonth.value == 12) {
                currentMonth.value = 1
                currentYear.value += 1
            } else {
                currentMonth.value += 1
            }
            onMonthChange(currentYear.value, currentMonth.value) },
            enabled = true
        ) {
            Icon(
                imageVector = Icons.Sharp.ArrowForward  ,
                contentDescription = "Go to Next Month")
        }

    }
}

fun getMonthName(month: Int): String {
    return SimpleDateFormat("MMMM", Locale.getDefault()).format(Calendar.getInstance().apply {
        set(Calendar.MONTH, month - 1)
    }.time)
}

@Composable
fun DayRecordedMoods(
    navController: NavController,
    modifier: Modifier = Modifier,
    day: Int,
    moods: List<MoodEntry>,
    setSelectedMoodIndex: (MoodEntry) -> Unit
    ) {

    Column {
        Text(
            text = "${getMonthName(moods.first().dateTime.month)} $day",
            fontSize = 20.sp,
            modifier = modifier.padding(start = 16.dp)
            )
        Spacer(modifier = modifier.height(16.dp))
        LazyRow (modifier = modifier.fillMaxWidth()){
            items(moods){ mood ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = mood.selectedImagesRes),
                        modifier = modifier
                            .padding(4.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .border(
                                width = 1.dp,
                                color = Color(0xFFA0A0A0),
                                shape = MaterialTheme.shapes.medium
                            )
                            .clickable {
                                navController.navigate("PreviousMoodScreen"); setSelectedMoodIndex(
                                mood
                            )
                            },

                        contentDescription = mood.feelingName
                    )
                   Text(text = mood.feelingName)
                }
            }
        }
    }

}

@Preview (showBackground = true)
@Composable
private fun DayRecordedMoodsPreview() {
    //DayRecordedMoods()
}

@Preview (showBackground = true)
@Composable
private fun ScrollItemsBarPreview() {
    //ScrollMonthsBar()
}

@Preview (showBackground = true)
@Composable
private fun MoodCalendarScreenPreview() {
   // MoodCalendarScreen()
}