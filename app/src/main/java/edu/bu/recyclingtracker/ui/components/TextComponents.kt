package edu.bu.recyclingtracker.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.theme.GlassColor

@Composable
fun headerText(text: String, color: Color = Color.Black) {
    Text(text,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = color)
}

@Composable
fun weightText(category: String, viewModel: LogRecyclablesViewModel, color: Color) {
    Text("${
        String.format("%.1f", viewModel.weights.value[category])
    } " +
            "lbs of ${category.lowercase()}", fontSize = 18.sp, modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        textAlign = TextAlign.Center,
        color = color
    )
}
