package com.example.testandroidcompose.ui.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.testandroidcompose.ui.AoenTypography
import com.example.testandroidcompose.ui.ui_test.fadeIn

@Composable
fun AoenButtonText(text: String, clickable: (() -> Unit)) {
    ConstraintLayout(
        Modifier.fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = clickable)
            .border(
                BorderStroke(width = 2.dp, color = Color.White),
                RoundedCornerShape(10.dp)
            ).fadeIn()
    ) {
        val (tv) = createRefs()
        Text(
            text = text,
            style = AoenTypography.button,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.constrainAs(tv) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        )
    }
}

@Composable
fun AoenButtonText(text: String) {
    ConstraintLayout(
        Modifier.fillMaxWidth()
            .height(60.dp)
            .border(
                BorderStroke(width = 2.dp, color = Color.White),
                RoundedCornerShape(10.dp)
            )
    ) {
        val (tv) = createRefs()
        Text(
            text = text,
            style = AoenTypography.button,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.constrainAs(tv) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}