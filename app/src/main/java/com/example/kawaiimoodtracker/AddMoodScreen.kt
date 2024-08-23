package com.example.kawaiimoodtracker

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kawaiimoodtracker.ui.theme.KawaiiMoodTrackerTheme

val images = listOf(
    R.drawable.expression_1,
    R.drawable.expression_2,
    R.drawable.expression_3,
    R.drawable.expression_4,
    R.drawable.expression_5,
    R.drawable.expression_6,
    R.drawable.expression_7,
    R.drawable.expression_8,
    R.drawable.expression_9,
    R.drawable.expression_10,
    R.drawable.expression_11,
    R.drawable.expression_12,
    R.drawable.expression_13,
    R.drawable.expression_14,
    R.drawable.expression_15,
    R.drawable.expression_16,
    R.drawable.expression_17,
    R.drawable.expression_18,
    R.drawable.expression_19,
    R.drawable.expression_20,
    R.drawable.expression_21,
    R.drawable.expression_22,
    R.drawable.expression_23,
    R.drawable.expression_24
)


@Composable
fun AddMoodScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    moodViewModel: MoodViewModel
) {

    val moodStateHolder = moodViewModel.moodStateHolder
    val selectedImageRes by moodStateHolder.selectedImageRes.observeAsState(R.drawable.baseline_add_circle)
    val text by moodStateHolder.text.observeAsState("")
    val firstMoodSubmitted by moodStateHolder.firstMoodSubmitted.observeAsState(false)
    val imageSelected by moodStateHolder.imageSelected.observeAsState(initial = false)

    Log.d("AddMoodScreen", "SelectedImageRes: $selectedImageRes")
    Log.d("AddMoodScreen", "Text: $text")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {

        Spacer(modifier = modifier.height(32.dp))

        if (firstMoodSubmitted) {
            CancelButton(navController, modifier = Modifier.align(Alignment.End))
        }

        HowAreYouText()


        ExpressionDisplay(
            images = images,
            onImageSelected = { imageRes ->
                moodStateHolder.setSelectedImageRes(imageRes)
                moodStateHolder.showImageSelector = false
                moodStateHolder.setImageSelected(value = true)
            },
            showImageSelector = moodStateHolder.showImageSelector,
            onShowImageSelector =  {moodStateHolder.showImageSelector = true},
            selectedImageRes = selectedImageRes
        )



        Spacer(modifier = modifier.height(16.dp))

        NameFeelingTextField(
            text = text,
            onTextChange = {moodStateHolder.setText(it)})

        Spacer(modifier = modifier.height(16.dp))

        AddMoodButton(
            navController = navController,
            onClickAddMood = {
                moodViewModel.addMoodEntry()
                moodStateHolder.setFirstMoodSubmitted(true)
                moodStateHolder.setSelectedImageRes(R.drawable.baseline_add_circle)
                moodStateHolder.reasonSubmitted = false
                moodStateHolder.setReasonText("")
                moodStateHolder.setReasonSubmittedText("")
                moodStateHolder.setText("")
            },
            imageSelected = imageSelected
        )


    }
}

@Composable
fun AddMoodButton(imageSelected: Boolean, navController: NavController, onClickAddMood: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = {
            onClickAddMood()
            navController.navigate("CurrentMoodScreen")
        },
        enabled = imageSelected,
        modifier = Modifier
            // .padding(24.dp)
            .height(46.dp)
            .width(224.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFE1EC),
        ),
        border = BorderStroke(1.dp, color = Color(0xFF9F4666))

    ) {
        Text(
            text = stringResource(id = R.string.AddButton),
            color = Color(0xFF9F4666)
        )
    }
}

@Composable
fun HowAreYouText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.how_are_you),
        textAlign = TextAlign.Center,
        fontSize = 32.sp,
        modifier = modifier
            .width(272.dp)
            .height(96.dp),
        lineHeight = 39.sp
    )
}

@Composable
fun ExpressionDisplay(
    images: List<Int>,
    onImageSelected: (Int) -> Unit,
    showImageSelector:Boolean,
    onShowImageSelector: () -> Unit,
    selectedImageRes:Int,
    modifier: Modifier = Modifier
) {

    Button(
            onClick = onShowImageSelector,
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
                .width(224.dp)
                .height(224.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF6F6F6)
            ),
            border = BorderStroke(1.dp, color = Color(0xFFA0A0A0))
        ) {
            Image(
                painter = painterResource(id = selectedImageRes),
                modifier = modifier
                    .fillMaxSize(),
//                    .width(67.dp)
//                    .height(61.dp),
                contentDescription = stringResource(id = R.string.add_expression),
                //colorFilter =  ColorFilter.tint(Color(0xFFFDBED4))
            )
        }

    if (showImageSelector){
        EmotionsGrid(
            images = images,
            onImageSelected = onImageSelected
        )
    }


}

@Preview
@Composable
private fun ExpressionDisplayPreview() {
   // ExpressionDisplay()
}



@Composable
fun EmotionsGrid(images: List<Int>, onImageSelected: (Int) -> Unit, modifier: Modifier = Modifier) {

    Dialog (onDismissRequest = {}) {
        Box(Modifier.background(color = Color(0xFFF6F6F6) )) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = modifier
                    .size(339.dp)
                    .padding(16.dp)
            ) {
                items(images.size){ index ->
                    ExpressionButton(
                        imagesRes = images[index],
                        onImageSelected = onImageSelected
                    )
                }
            }
        }
    }
}

@Composable
fun NameFeelingTextField(text: String, onTextChange: (String) -> Unit, modifier: Modifier = Modifier) {

    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(
            text = stringResource(id = R.string.name_feeling),
        )},
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .width(257.dp)
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.medium,
                color = Color(0xFFA0A0A0)
            ),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0xFFFFE1EC),
            unfocusedContainerColor = Color.Transparent,
            focusedLabelColor = Color(0xFF9F4666),
            focusedTextColor = Color(0xFF9F4666),
            unfocusedTextColor = Color(0xFFFFE1EC)
        ),

    )
}

@Preview
@Composable
private fun NameFeelingTextFieldPreview() {
    //NameFeelingTextField()
}
@Composable
fun ExpressionButton(imagesRes: Int, onImageSelected: (Int) -> Unit, modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(id = imagesRes),
        contentDescription = "",
        modifier = modifier
            .padding(4.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onImageSelected(imagesRes) }
            .border(
                width = 1.dp,
                color = Color(0xFFA0A0A0),
                shape = MaterialTheme.shapes.medium
            )
    )
}

@Composable
fun CancelButton(navController: NavController, modifier: Modifier = Modifier) {

        IconButton(
            onClick = { navController.navigate("CurrentMoodScreen") },
            modifier = modifier
        ) {
            Icon(
                imageVector = Icons.Sharp.Close,
                modifier = modifier.size(48.dp),
                contentDescription = ""
            )
        }

}

@Preview (showBackground = true)
@Composable
private fun CancelButtonPreview() {
    //CancelButton()
}


@Preview (showBackground = true)
@Composable
private fun EmotionsGridPreview() {
    //EmotionsGrid()
}

@Preview (showBackground = true)
@Composable
private fun ExpressionButtonPreview() {
   // ExpressionButton()
}

@Preview (showBackground = true)
@Composable
private fun AddMoodScreenPreview() {
   KawaiiMoodTrackerTheme {
       //AddMoodScreen()
   }
}