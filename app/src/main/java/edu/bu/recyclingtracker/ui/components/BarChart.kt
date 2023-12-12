package edu.bu.recyclingtracker.ui.components

import android.graphics.Paint
import android.graphics.PathEffect
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.Math.round


/*
This composable creates a custom bar chart to represent Recyclable Item totals.
The code was adapted from the tutorial linked here:
https://medium.com/@developerchunk/create-custom-bargraph-with-scales-in-jetpack-compose-android-studio-kotlin-deadba24fd9b
 */

@Composable
fun BarGraph(
    graphBarData: List<Float>,
    xAxisLabels: List<String>,
    barData_: List<Int>,
    height: Dp,
    roundType: BarType,
    barWidth: Dp,
    barColor: Color,
    barArrangement: Arrangement.Horizontal,
    category: String
) {

    val barData by remember {
        mutableStateOf(barData_+0)
    }

    // Get screen height and width
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp

    // bottom height
    val xAxisScaleHeight = 40.dp

    val yAxisScaleSpacing by remember {
        mutableStateOf(100f)
    }

    val yAxisTextWidth by remember {
        mutableStateOf(100.dp)
    }

    // bar shape
    val barShape =
        when(roundType) {
            BarType.CIRCULAR_TYPE -> CircleShape
            BarType.TOP_CURVED -> RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp)
        }

    val density = LocalDensity.current
    // y-axis scale text paint
    val textPaint = remember(density) {
        Paint().apply {
            color = Color.Black.hashCode()
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx()}
        }
    }

    val yCoordinates = mutableListOf<Float>()

    val pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    val lineHeightXAxis = 10.dp
    // height of horizontal line over x-axis scale
    val horizontalLineHeight = 5.dp

    Column {
        Text(
            category, modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp),
            contentAlignment = Alignment.TopStart
        ) {

            // Layer 1
            // y-axis scale and horizontal dotted lines on graph indicating y-axis scale

            Column(
                modifier = Modifier
                    .padding(top = xAxisScaleHeight, end = 3.dp)
                    .height(height)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Canvas(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxSize()
                ) {

                    // Y-Axis Scale Text
                    val yAxisScaleText = (barData.max()) / 3f
                    (0..3).forEach { i ->
                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                round(barData.min() + yAxisScaleText * i).toString(),
                                30f,
                                size.height - yAxisScaleSpacing - i * size.height / 3f,
                                textPaint
                            )
                        }
                        yCoordinates.add(size.height - yAxisScaleSpacing - i * size.height / 3f)
                    }

                    // horizontal dotted lines on graph indicating y-axis scale
                    (1..3).forEach {
                        drawLine(
                            start = Offset(x = yAxisScaleSpacing + 30f, y = yCoordinates[it]),
                            end = Offset(x = size.width, y = yCoordinates[it]),
                            color = Color.Gray,
                            strokeWidth = 5f,
                            pathEffect = pathEffect
                        )
                    }
                }
            }

            // Layer 2
            // Graph with bar graph and X-axis scale
            Box(
                modifier = Modifier
                    .padding(start = 50.dp)
                    .width(width.dp - yAxisTextWidth)
                    .height(height + xAxisScaleHeight),
                contentAlignment = Alignment.BottomCenter
            ) {

                Row(
                    modifier = Modifier
                        .width(width.dp - yAxisTextWidth),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = barArrangement
                ) {
                    // Graph
                    graphBarData.forEachIndexed { index, value ->

                        var animationTriggered by remember {
                            mutableStateOf(false)
                        }
                        val graphBarHeight by animateFloatAsState(
                            targetValue = if (animationTriggered) value else 0f,
                            animationSpec = tween(
                                durationMillis = 1000,
                                delayMillis = 0
                            )
                        )
                        LaunchedEffect(key1 = true) {
                            animationTriggered = true
                        }

                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            // Each Graph
                            Box(
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                                    .clip(barShape)
                                    .width(barWidth)
                                    .height(height - 10.dp)
                                    .background(Color.Transparent),
                                contentAlignment = Alignment.BottomCenter

                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(barShape)
                                        .fillMaxWidth()
                                        .fillMaxHeight(graphBarHeight)
                                        .background(barColor)
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .height(xAxisScaleHeight),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Box(
                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(
                                                bottomStart = 2.dp,
                                                bottomEnd = 2.dp
                                            )
                                        )
                                        .width(horizontalLineHeight)
                                        .height(lineHeightXAxis)
                                        .background(color = Color.Gray)
                                )

                                // scale x-axis
                                Text(
                                    modifier = Modifier
                                        .padding(top = 16.dp)
                                        .rotate(270f),
//                                text = xAxisScaleData[index].toString(),
                                    text = xAxisLabels[index],
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black
                                )

                            }
                        }
                    }
                }

                // horizontal line on x-axis on the graph
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .padding(bottom = xAxisScaleHeight + 3.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .fillMaxWidth()
                            .height(horizontalLineHeight)
                            .background(Color.Gray)
                    )
                }
            }
        }
    }
}
