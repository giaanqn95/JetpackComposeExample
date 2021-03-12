package com.example.testandroidcompose.ui.ui_test

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun ScaffoldTest() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { Text("Drawer content") },
        topBar = {
            TopAppBar(
                title = { Text("Simple Scaffold Screen") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch { scaffoldState.drawerState.open() }
                        }
                    ) {
                        Image(Icons.Default.Menu, modifier = Modifier)
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Inc") },
                onClick = { /* fab click handler */ }
            )
        }) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(randomList()) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(randomColor())
                )
            }
        }
    }
}

@Composable
fun ScaffoldMoreFancy() {
    val scaffoldState = rememberScaffoldState()

// Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
    val sharpEdgePercent = -50f
    val roundEdgePercent = 45f
// Start with sharp edges
    val animatedProgress = remember { Animatable(sharpEdgePercent) }
// Create a coroutineScope for the animation
    val coroutineScope = rememberCoroutineScope()
// animation value to animate shape
    val progress = animatedProgress.value.roundToInt()

// When progress is 0, there is no modification to the edges so we are just drawing a rectangle.
// This allows for a smooth transition between cut corners and round corners.
    val fabShape = when {
        progress < 0 -> {
            CutCornerShape(abs(progress))
        }
//        progress == roundEdgePercent.toInt() -> {
//            CircleShape
//        }
        else -> {
            RoundedCornerShape(progress)
        }
    }
// lambda to call to trigger shape animation
    val changeShape: () -> Unit = {
        val target = animatedProgress.targetValue
        val nextTarget = if (target == roundEdgePercent) sharpEdgePercent else roundEdgePercent
        coroutineScope.launch {
            animatedProgress.animateTo(
                targetValue = nextTarget,
                animationSpec = TweenSpec(durationMillis = 600)
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { Text("Drawer content") },
        topBar = { TopAppBar(title = { Text("Scaffold with bottom cutout") }) },
        bottomBar = {
            BottomAppBar(cutoutShape = fabShape) {
                IconButton(
                    onClick = {
                        coroutineScope.launch { scaffoldState.drawerState.open() }
                    }
                ) {
                    Image(Icons.Filled.Menu)
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("") },
                onClick = changeShape,
                shape = fabShape
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true,
        bodyContent = { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                items(randomList()) {
                    var color = randomColor()
                    var colorFinish = randomColor()
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(color)
                            .clickable(onClick = {
                            })
                    )
                }
            }
        }
    )
}

fun randomList(): ArrayList<Int> {
    val listString: ArrayList<Int> = ArrayList()
    for (i in 0..50) {
        listString.add(i)
    }
    return listString
}

fun randomColor(): Color {
    val rnd = Random()
    return Color(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}