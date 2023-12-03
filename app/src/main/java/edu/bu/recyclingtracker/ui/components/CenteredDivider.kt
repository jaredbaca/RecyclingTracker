package edu.bu.recyclingtracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CenteredDivider(paddingValue: Int) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = paddingValue.dp, end = paddingValue.dp, top = 24.dp, bottom = 24.dp)
            .height(1.dp)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
//            .padding(vertical = 48.dp)
    )

}