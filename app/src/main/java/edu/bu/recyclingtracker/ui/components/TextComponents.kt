package edu.bu.recyclingtracker.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun headerText(text: String, color: Color = Color.Black) {
    Text(text,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = color)
}
