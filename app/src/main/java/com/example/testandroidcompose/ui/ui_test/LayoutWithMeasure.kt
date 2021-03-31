package com.example.testandroidcompose.ui.ui_test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.testandroidcompose.R

@Composable
fun MyCustomLayout(modifier: Modifier = Modifier) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color.Magenta, Color.Blue),
        startX = 0.0f,
        endX = 500.0f,
        tileMode = TileMode.Clamp
    )
    Layout(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(brush = gradientBrush)
            .wrapContentWidth()
            .clickable(onClick = {})
            .height(120.dp),
        content = {
            Text("Text dai oi la dai")
            Image(
                imageResource(id = R.drawable.tom_boy),
                modifier = Modifier.clip(CircleShape)
            )
        }
    ) { measurables, constraints ->
        val textWidth = constraints.maxWidth * 0.75f
        val textPlaceable = measurables[0].measure(Constraints.fixedWidth(textWidth.toInt()))
        val imageSize = constraints.maxWidth * 0.45f
        val imagePlaceable = measurables[1].measure(Constraints.fixedWidth(imageSize.toInt()))
        layout(width = constraints.maxWidth, height = constraints.maxHeight) {
            textPlaceable.place(x = 0, y = (constraints.maxHeight - textPlaceable.height) / 2)
            imagePlaceable.place(
                x = textWidth.toInt(),
                y = (constraints.maxHeight - imagePlaceable.height) / 2
            )
        }

    }
}