package com.example.testandroidcompose.notification

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.More
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.testandroidcompose.ui.typography

@Composable
fun HeaderNotification(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxWidth().height(45.dp)) {
        val (text, image) = createRefs()
        Text(
            text = "Notifications",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().constrainAs(text) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            style = typography.h6,
        )
        Image(
            Icons.Default.More,
            alignment = Alignment.CenterEnd,
            modifier = Modifier.constrainAs(image) {
                end.linkTo(parent.end, 10.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }.clickable(onClick = { Log.d("AAAAA", " Click icon header") })
        )
    }
}

@Composable
fun ListNotifications(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier) {
        val (header, lazy, navigation) = createRefs()
        HeaderNotification(modifier = modifier.constrainAs(header){
            top.linkTo(parent.top)
        })
        LazyColumn(modifier = modifier.fillMaxWidth().constrainAs(lazy) {
            bottom.linkTo(navigation.top)
            top.linkTo(header.bottom)
        }) {
            items(randomList()) {
                ItemNotifications(mess = it)
            }
        }
        BottomNavigation(modifier = modifier.fillMaxWidth().constrainAs(navigation) {
            bottom.linkTo(parent.bottom)
        }) {

        }
    }
}

//layout: ListItem
@Composable
fun ItemNotifications(mess: String, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier.padding(10.dp).clickable(onClick = { Log.d("Click Item", mess) })
            .fillMaxWidth()
    ) {
        val (surface, column, divider) = createRefs()
        Surface(
            modifier = Modifier.preferredSize(50.dp).constrainAs(surface) {
                start.linkTo(parent.start)
            },
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            val imageModifier = remember {
                Modifier.preferredHeight(180.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
            }
            Image(
                Icons.Default.Message,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )

        }
        Column(
            modifier = Modifier.padding(start = 8.dp).constrainAs(column) {
                start.linkTo(surface.end)
            }
        ) {
            Text("$mess heade   r", fontWeight = FontWeight.Bold)
            Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                Text(mess, style = MaterialTheme.typography.body2)
            }
        }
        Divider(Modifier.constrainAs(divider) {
            top.linkTo(surface.bottom, 5.dp)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
        })
    }
}

fun randomList(): ArrayList<String> {
    val listString: ArrayList<String> = ArrayList()
    for (i in 0..20) {
        listString.add("Anh yeu em $i")
    }
    return listString
}

@Composable
fun Divider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = DividerAlpha),
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp
) {
    Box(
        modifier.fillMaxWidth()
            .preferredHeight(thickness)
            .background(color = color)
    )
}

private const val DividerAlpha = 0.12f