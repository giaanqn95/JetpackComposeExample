package com.example.testandroidcompose.ui.ui_test

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.unit.dp
import com.example.testandroidcompose.second.TodoIcon
import kotlinx.coroutines.launch

@Composable
fun TestCanvas() {
    Canvas(modifier = Modifier.size(100.dp)) {
        drawRect(Color.Magenta)
        inset(10.0f) {
            drawLine(
                color = Color.Red,
                start = Offset.Zero,
                end = Offset(size.width, size.height),
                strokeWidth = 5.0f
            )
        }
    }
}

@Composable
fun saveStateScrolled() {
    // Create ScrollState to own it and be able to control scroll behaviour of scrollable Row below
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    Column {
        Row(Modifier.horizontalScroll(scrollState)) {
            repeat(200) { index ->
                Image(Icons.Default.Message, modifier = Modifier.height(50.dp).width(50.dp))
            }
        }
        // Controls for scrolling
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Scroll")
            Button(
                onClick = {
                    scope.launch { scrollState.scrollTo(scrollState.value - 1000) }
                }
            ) {
                Text("< -")
            }
            Button(
                onClick = {
                    scope.launch { scrollState.scrollBy(10000f) }
                }
            ) {
                Text("--- >")
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Smooth Scroll")
            Button(
                onClick = {
                    scope.launch { scrollState.smoothScrollTo(scrollState.value - 1000) }
                }
            ) {
                Text("< -")
            }
            Button(
                onClick = {
                    scope.launch { scrollState.smoothScrollBy(10000f) }
                }
            ) {
                Text("--- >")
            }
        }
    }
}

val gradientBrush = Brush.horizontalGradient(
    colors = listOf(Color.Red, Color.Blue, Color.Green),
    startX = 0.0f,
    endX = 500.0f,
    tileMode = TileMode.Repeated
)

@Composable
fun ViewGradiant() {
    Text(
        "Text with gradient border",
        modifier = Modifier.border(width = 2.dp, brush = gradientBrush, shape = CircleShape)
            .padding(10.dp)
    )
}

@Composable
fun horizontalScrollGradientColor() {
    val scrollState = rememberScrollState()
    val gradient = Brush.horizontalGradient(
        listOf(Color.Red, Color.Blue, Color.Green), 0.0f, 10000.0f, TileMode.Repeated
    )
    Box(
        Modifier
            .horizontalScroll(scrollState)
            .size(width = 10000.dp, height = 200.dp)
            .background(brush = gradient)
    )
}

@Composable
fun progressSemantics() {
    val progress = 0.5f // emulate progress from some state
    Box(
        Modifier
            .progressSemantics(progress)
            .size((progress * 100).dp, 4.dp)
            .background(color = Color.Cyan)
    )
}

@Composable
fun progressSemanticsWithoutSize() {
    Box(Modifier.progressSemantics().background(color = Color.Cyan)) {
        Text("Operation is on progress")
    }
}

@Composable
fun verticalScrollGradientColor() {
    val scrollState = rememberScrollState()
    val gradient = Brush.verticalGradient(
        listOf(Color.Red, Color.Blue, Color.Green), 0.0f, 10000.0f, TileMode.Repeated
    )
    Box(
        Modifier
            .verticalScroll(scrollState)
            .fillMaxWidth()
            .height(10000.dp)
            .background(brush = gradient)
    )
}