package com.example.testandroidcompose.ui.ui_test

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun ColumnBox() {
    Column {
        /* The child with no weight will have the specified size. */
        Box(Modifier.size(40.dp, 80.dp).background(Color.Magenta))
        // Has weight, the child will occupy half of the remaining height.
        Box(Modifier.width(40.dp).weight(1f).background(Color.Yellow))
        // Has weight and does not fill, the child will occupy at most half of the remaining height.
        // Therefore it will occupy 80.dp (its preferred height) if the assigned height is larger.
        Box(
            Modifier.size(40.dp, 80.dp)
                .weight(1f, fill = false)
                .background(Color.Green)
        )
    }
}

@Composable
fun ColumnBox2() {
    Row {
        // The child with no weight will have the specified size.
        Box(Modifier.size(40.dp, 80.dp).background(Color.Magenta))
        // Has weight, the child will occupy half of the remaining width.
        Box(Modifier.height(40.dp).weight(1f).background(Color.Yellow))
        // Has weight and does not fill, the child will occupy at most half of the remaining width.
        // Therefore it will occupy 80.dp (its preferred width) if the assigned width is larger.
        Box(
            Modifier.size(80.dp, 40.dp)
                .weight(1f, fill = true)
                .background(Color.Green)
        )
    }
}

@Composable
fun RowWithSpacer() {
    Row {
        Box(Modifier.size(100.dp).background(Color.Red))
        Spacer(Modifier.width(20.dp))
        Box(Modifier.size(100.dp).background(Color.Magenta))
        Spacer(Modifier.weight(1f))
        Box(Modifier.size(100.dp).background(Color.Black))
    }
}

@Composable
fun AbsoluteOffSet() {
    // This text will be offset (10.dp, 20.dp) from the center of the available space.
    Text(
        "Layout offset modifier sample",
        Modifier.fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .absoluteOffset(10.dp, 20.dp)
    )
}

@Composable
fun AbsoluteOffSetClick() {
    val offset = remember { mutableStateOf(0) }
    Text(
        "Layout offset modifier sample",
        Modifier
            .clickable { offset.value += 10 }
            .absoluteOffset { IntOffset(offset.value, offset.value) }
    )
}

@Composable
fun AbsolutePadding() {
    Box(Modifier.background(color = Color.Gray)) {
        Box(
            Modifier.absolutePadding(left = 20.dp, top = 30.dp, right = 20.dp, bottom = 30.dp)
                .size(50.dp)
                .background(Color.Blue)
        )
    }
}

@Composable
fun AspectRatio() {
    Box(Modifier.background(color = Color.Gray)) {
        Box(
            Modifier.absolutePadding(left = 20.dp, top = 30.dp, right = 20.dp, bottom = 30.dp)
                .size(50.dp)
                .background(Color.Blue)
        )
    }
}
