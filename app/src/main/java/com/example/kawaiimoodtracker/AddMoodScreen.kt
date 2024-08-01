package com.example.kawaiimoodtracker

import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kawaiimoodtracker.ui.theme.KawaiiMoodTrackerTheme

@Composable
fun AddMoodScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.how_are_you),
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            modifier = modifier
                .width(272.dp)
                .height(96.dp)
        )
        Button(
            onClick = { /*TODO*/ },
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
                .width(224.dp)
                .height(224.dp)
                //.padding(48.dp)

        ) {
           Icon(
               imageVector = Icons.Sharp.AddCircle,
               modifier = modifier
                   .width(67.dp)
                   .height(61.dp),
               contentDescription = stringResource(id = R.string.add_expression))
        }
        Button(
            onClick = { /*TODO*/ },
            enabled = false,
            modifier = Modifier
                .padding(24.dp)
                .height(46.dp)
                .width(224.dp)

        ) {
          Text(
              text = stringResource(id = R.string.AddButton)
          )
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun AddMoodScreenPreview() {
   KawaiiMoodTrackerTheme {
       AddMoodScreen()
   }
}