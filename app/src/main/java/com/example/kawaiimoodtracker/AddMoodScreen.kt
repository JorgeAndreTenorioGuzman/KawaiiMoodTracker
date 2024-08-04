package com.example.kawaiimoodtracker

import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material.icons.sharp.ArrowBack
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kawaiimoodtracker.ui.theme.KawaiiMoodTrackerTheme

@Composable
fun AddMoodScreen(navController: NavController, modifier: Modifier = Modifier) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {

        CancelButton(navController, modifier = Modifier.align(Alignment.End))

        HowAreYouText()

        ExpressionDisplay()

        Spacer(modifier = modifier.height(16.dp))

        NameFeelingTextField()

        Spacer(modifier = modifier.height(16.dp))

        AddMoodButton()

        
    }
}

@Composable
fun AddMoodButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
        enabled = true,
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
fun ExpressionDisplay(modifier: Modifier = Modifier) {
    Button(
            onClick = { /*TODO*/ },
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
                .width(224.dp)
                .height(224.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF6F6F6)
            ),
            border = BorderStroke(1.dp, color = Color(0xFFA0A0A0))
        ) {
            Icon(
                imageVector = Icons.Sharp.AddCircle,
                modifier = modifier
                    .width(67.dp)
                    .height(61.dp),
                contentDescription = stringResource(id = R.string.add_expression),
                tint = Color(0xFFFDBED4)
            )
        }


}

@Composable
fun EmotionsGrid(modifier: Modifier = Modifier) {

    Box(Modifier.background(color = Color(0xFFF6F6F6) )) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = modifier
                .size(339.dp)
                .padding(16.dp)
        ) {
            items(24){ index ->
                ExpressionButton()
            }
        }
    }
}

@Composable
fun NameFeelingTextField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it},
        label = { Text(
            text = stringResource(id = R.string.name_feeling),
        )},
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .width(257.dp)
            .height(48.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0xFFD9D9D9),
            unfocusedContainerColor = Color(0xFFD9D9D9)
        ),



    )
}
@Composable
fun ExpressionButton(modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(id = R.drawable.awesome_expression),
        contentDescription = "",
        modifier = modifier
            .padding(4.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { }
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
    EmotionsGrid()
}

@Preview (showBackground = true)
@Composable
private fun ExpressionButtonPreview() {
    ExpressionButton()
}

@Preview (showBackground = true)
@Composable
private fun AddMoodScreenPreview() {
   KawaiiMoodTrackerTheme {
       //AddMoodScreen()
   }
}