package com.example.testandroidcompose.ui.ui_test

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.testandroidcompose.ui.AoenTypography

@Composable
fun animateColor(
    targetValue: Color,
    animationSpec: AnimationSpec<Color>,
    onFinished: (Color) -> Unit
): Color {
    // Creates an Animatable of Color, and remembers it.
    val color = remember { Animatable(targetValue) }
    val finishedListener = rememberUpdatedState(onFinished)
    // Launches a new coroutine whenever the target value or animation spec has changed. This
    // automatically cancels the previous job/animation.
    LaunchedEffect(targetValue, animationSpec) {
        color.animateTo(targetValue, animationSpec)
        // Invokes finished listener. This line will not be executed if the job gets canceled
        // halfway through an animation.
        finishedListener.value(targetValue)
    }
    return color.value
}

@ExperimentalAnimationApi
@Composable
fun animateVisibility() {
    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            // Start the slide from 40 (pixels) above where the content is supposed to go, to
            // produce a parallax effect
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "animateVisibility Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }

    Crossfade(current = "A") { screen ->
        when (screen) {
            "A" -> Text("Page A")
            "B" -> Text("Page B")
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun expandHorizontally() {
    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible = visible,
        enter = expandHorizontally(
            // Set the start width to 20 (pixels), 0 by default
            initialWidth = { 20 }
        ),
        exit = shrinkHorizontally(
            // Shrink towards the end (i.e. right edge for LTR, left edge for RTL). The default
            // direction for the shrink is towards [Alignment.Start]
            shrinkTowards = Alignment.End,
            // Set the end width for the shrink animation to a quarter of the full width.
            targetWidth = { fullWidth -> fullWidth / 4 },
            // Overwrites the default animation with tween for this shrink animation.
            animSpec = tween()
        )
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "expandHorizontally Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun expandInAnimatedVisibility() {
    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible,
        enter = expandIn(
            // Overwrites the corner of the content that is first revealed
            expandFrom = Alignment.BottomStart,
            // Overwrites the initial size to 50 pixels by 50 pixels
            initialSize = { IntSize(50, 50) },
            // Overwrites the default spring animation with tween
            animSpec = tween(100, easing = LinearOutSlowInEasing)
        ),
        exit = shrinkOut(
            // Overwrites the area of the content that the shrink animation will end on. The
            // following parameters will shrink the content's clip bounds from the full size of the
            // content to 1/10 of the width and 1/5 of the height. The shrinking clip bounds will
            // always be aligned to the CenterStart of the full-content bounds.
            shrinkTowards = Alignment.CenterStart,
            // Overwrites the target size of the shrinking animation.
            targetSize = { fullSize -> IntSize(fullSize.width / 10, fullSize.height / 5) },
            animSpec = tween(100, easing = FastOutSlowInEasing)
        )
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "expandInAnimatedVisibility Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun expandVerticallyAnimatedVisibility() {

    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible,
        // Sets the initial height of the content to 20, revealing only the top of the content at
        // the beginning of the expanding animation.
        enter = expandVertically(
            expandFrom = Alignment.Top,
            initialHeight = { 20 }
        ),
        // Shrinks the content to half of its full height via an animation.
        exit = shrinkVertically(
            targetHeight = { fullHeight -> fullHeight / 2 },
            animSpec = tween()
        )
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "expandVerticallyAnimatedVisibility Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun fadeInAnimatedVisibility() {

    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
            initialAlpha = 0.4f
        ),
        exit = fadeOut(
            // Overwrites the default animation with tween
            animSpec = tween(durationMillis = 250)
        )
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "fadeInAnimatedVisibility Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun fadeOutAnimatedVisibility() {

    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
            initialAlpha = 0.4f
        ),
        exit = fadeOut(
            // Overwrites the default animation with tween
            animSpec = tween(durationMillis = 250)
        )
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "fadeOutAnimatedVisibility Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun shrinkHorizontallyAnimatedVisibility() {

    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible = visible,
        enter = expandHorizontally(
            // Set the start width to 20 (pixels), 0 by default
            initialWidth = { 20 }
        ),
        exit = shrinkHorizontally(
            // Shrink towards the end (i.e. right edge for LTR, left edge for RTL). The default
            // direction for the shrink is towards [Alignment.Start]
            shrinkTowards = Alignment.End,
            // Set the end width for the shrink animation to a quarter of the full width.
            targetWidth = { fullWidth -> fullWidth / 4 },
            // Overwrites the default animation with tween for this shrink animation.
            animSpec = tween()
        )
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "shrinkHorizontallyAnimatedVisibility Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun shrinkOutAnimatedVisibility() {

    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible,
        enter = expandIn(
            // Overwrites the corner of the content that is first revealed
            expandFrom = Alignment.BottomStart,
            // Overwrites the initial size to 50 pixels by 50 pixels
            initialSize = { IntSize(50, 50) },
            // Overwrites the default spring animation with tween
            animSpec = tween(100, easing = LinearOutSlowInEasing)
        ),
        exit = shrinkOut(
            // Overwrites the area of the content that the shrink animation will end on. The
            // following parameters will shrink the content's clip bounds from the full size of the
            // content to 1/10 of the width and 1/5 of the height. The shrinking clip bounds will
            // always be aligned to the CenterStart of the full-content bounds.
            shrinkTowards = Alignment.CenterStart,
            // Overwrites the target size of the shrinking animation.
            targetSize = { fullSize -> IntSize(fullSize.width / 10, fullSize.height / 5) },
            animSpec = tween(100, easing = FastOutSlowInEasing)
        )
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "shrinkOutAnimatedVisibility Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun shrinkVerticallyAnimatedVisibility() {

    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible,
        // Sets the initial height of the content to 20, revealing only the top of the content at
        // the beginning of the expanding animation.
        enter = expandVertically(
            expandFrom = Alignment.Top,
            initialHeight = { 20 }
        ),
        // Shrinks the content to half of its full height via an animation.
        exit = shrinkVertically(
            targetHeight = { fullHeight -> fullHeight / 2 },
            animSpec = tween()
        )
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "shrinkVerticallyAnimatedVisibility Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun slideInAnimatedVisibility() {

    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible,
        enter = slideIn(
            // Specifies the starting offset of the slide-in to be 1/4 of the width to the right,
            // 100 (pixels) below the content position, which results in a simultaneous slide up
            // and slide left.
            { fullSize -> IntOffset(fullSize.width / 4, 100) },
            tween(100, easing = LinearOutSlowInEasing)
        ),
        exit = slideOut(
            // The offset can be entirely independent of the size of the content. This specifies
            // a target offset 180 pixels to the left of the content, and 50 pixels below. This will
            // produce a slide-left combined with a slide-down.
            { IntOffset(-180, 50) },
            tween(100, easing = FastOutSlowInEasing)
        )
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
                "slideInAnimatedVisibility Content to appear/disappear",
                Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun slideInHorizontallyAnimatedVisibility() {

    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            // Offsets the content by 1/3 of its width to the left, and slide towards right
            initialOffsetX = { fullWidth -> -fullWidth / 3 },
            // Overwrites the default animation with tween for this slide animation.
            animSpec = tween(durationMillis = 50)
        ) + fadeIn(
            // Overwrites the default animation with tween
            animSpec = tween(durationMillis = 50)
        ),
        exit = slideOutHorizontally(
            // Overwrites the ending position of the slide-out to 50 (pixels) to the right
            targetOffsetX = { 50 },
            animSpec = spring(stiffness = Spring.StiffnessHigh)
        ) + fadeOut()
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "slideInHorizontallyAnimatedVisibility Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun slideInVerticallyAnimatedVisibility() {

    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            // Start the slide from 40 (pixels) above where the content is supposed to go, to
            // produce a parallax effect
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "slideInVerticallyAnimatedVisibility Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun slideOutAnimatedVisibility() {

    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible,
        enter = slideIn(
            // Specifies the starting offset of the slide-in to be 1/4 of the width to the right,
            // 100 (pixels) below the content position, which results in a simultaneous slide up
            // and slide left.
            { fullSize -> IntOffset(fullSize.width / 4, 100) },
            tween(100, easing = LinearOutSlowInEasing)
        ),
        exit = slideOut(
            // The offset can be entirely independent of the size of the content. This specifies
            // a target offset 180 pixels to the left of the content, and 50 pixels below. This will
            // produce a slide-left combined with a slide-down.
            { IntOffset(-180, 50) },
            tween(100, easing = FastOutSlowInEasing)
        )
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "slideOutAnimatedVisibility Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun slideOutHorizontally() {

    var visible by remember { mutableStateOf(true) }
    Button(onClick = { visible = !visible }) {

    }
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            // Offsets the content by 1/3 of its width to the left, and slide towards right
            initialOffsetX = { fullWidth -> -fullWidth / 3 },
            // Overwrites the default animation with tween for this slide animation.
            animSpec = tween(durationMillis = 50)
        ) + fadeIn(
            // Overwrites the default animation with tween
            animSpec = tween(durationMillis = 50)
        ),
        exit = slideOutHorizontally(
            // Overwrites the ending position of the slide-out to 50 (pixels) to the right
            targetOffsetX = { 50 },
            animSpec = spring(stiffness = Spring.StiffnessHigh)
        ) + fadeOut()
    ) {
        // Content that needs to appear/disappear goes here:
        Text(
            "slideOutHorizontally Content to appear/disappear",
            Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun slideOutVerticallyAnimatedVisibility() {
    var expanded by remember { mutableStateOf(true) }
    Button(onClick = { expanded = !expanded }) {

    }
    FloatingActionButton(
        onClick = { expanded = !expanded },
        modifier = with(ColumnScope) { Modifier.align(Alignment.CenterHorizontally) }
    ) {
        Row(Modifier.padding(start = 12.dp, end = 12.dp)) {
            Image(
                Icons.Default.Favorite,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            AnimatedVisibility(
                expanded,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(modifier = Modifier.padding(start = 12.dp), text = "Favorite")
            }
        }
    }
    Spacer(Modifier.height(20.dp))
}

@ExperimentalAnimationApi
@Composable
fun anotherAnimatedVisibility() {
    var itemIndex by remember { mutableStateOf(0) }
    val colors = listOf(Color.Red, Color.Green, Color.Blue)
    Column(
        Modifier.fillMaxWidth().clickable {
            itemIndex = (itemIndex + 1) % colors.size
        }
    ) {
        colors.forEachIndexed { i, color ->
            // By default ColumnScope.AnimatedVisibility expands and shrinks new content while
            // fading.
            AnimatedVisibility(i <= itemIndex) {
                Box(Modifier.height(40.dp).fillMaxWidth().background(color))
            }
        }
    }
}

@Composable
fun animateContentSize() {
    val shortText = "Hi"
    val longText = "Very long text\nthat spans across\nmultiple lines"
    var short by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .background(
                Color.Blue,
                RoundedCornerShape(15.dp)
            )
            .clickable { short = !short }
            .padding(20.dp)
            .wrapContentSize()
            .animateContentSize()
    ) {
        Text(
            if (short) {
                shortText
            } else {
                longText
            },
            style = AoenTypography.button.copy(color = Color.White)
        )
    }
}

@Composable
fun InfiniteAnimationDemo() {
    // Create a mutable state for alpha, and update it in the animation.
    val alpha = remember { mutableStateOf(1f) }
    LaunchedEffect(Unit) {
        // Animate from 1f to 0f using an infinitely repeating animation
        animate(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse
            )
        ) { value, /* velocity */ _ ->
            // Update alpha mutable state with the current animation value
            alpha.value = value
        }
    }
    Box(Modifier.fillMaxSize()) {
        Image(
            Icons.Filled.Favorite,
            modifier = Modifier.align(Alignment.Center)
                .graphicsLayer(
                    scaleX = 3.0f,
                    scaleY = 3.0f,
                    alpha = alpha.value
                ),
        )
    }
}

fun Modifier.fadeIn(): Modifier = composed {
    // Creates an `Animatable` and remembers it.
    val alphaAnimation = remember { Animatable(0f) }
    // Launches a coroutine for the animation when entering the composition.
    // Uses `alphaAnimation` as the subject so the job in `LaunchedEffect` will run only when
    // `alphaAnimation` is created, which happens one time when the modifier enters
    // composition.
    LaunchedEffect(alphaAnimation) {
        // Animates to 1f from 0f for the fade-in, and uses a 500ms tween animation.
        alphaAnimation.animateTo(
            targetValue = 1f,
            // Default animationSpec uses [spring] animation, here we overwrite the default.
            animationSpec = tween(1000)
        )
    }
    this.graphicsLayer(alpha = alphaAnimation.value)
}