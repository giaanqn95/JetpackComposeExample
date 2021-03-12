package com.example.testandroidcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.testandroidcompose.second.SecondActivity
import com.example.testandroidcompose.ui.TestAndroidComposeTheme
import com.example.testandroidcompose.ui.typography


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold {
                Column {
                    Surface(color = Color.Blue) { Text(text = "asdasd") }
                    Greeting(name = "An Dep Trai")
                    NewsStory()
                    Counter()
                    PhotographerCard(context = this@MainActivity)
                }
            }
        }
    }
}

@Composable
fun NewsStory() {
    Column(modifier = Modifier.padding(16.dp)) {
        val imageModifier = remember {
            Modifier.preferredHeight(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
        }
        Image(
            imageResource(R.drawable.tom_boy),
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.preferredHeight(10.dp))
        Text(
            "A day wandering through the sandhills " +
                    "in Shark Fin Cove, and a few of the " +
                    "sights I saw",
            style = typography.h6
        )

        Text("A day in Shark Fin Cove")
        Text("Davenport, California")
        Text("December 2018")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun TestView() {
    Text(text = "An Dep Trai")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestAndroidComposeTheme {
        Greeting("Android")
    }
}

@Preview
@Composable
fun PreviewNewStory() {
    TestAndroidComposeTheme {
        NewsStory()
    }
}

@Composable
fun Counter() {
    val count = remember { mutableStateOf(0) }

    Button(onClick = { count.value++ }) {
        Text("I've been clicked ${count.value} times")
    }
}

@Composable
fun EditText() {
    val text = remember { mutableStateOf("") }

//    TextField(value = text, onValueChange = {
//        text.value = it
//    },
//    textStyle = typography.body1)
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier, context: Context) {
    Row(
        modifier = modifier.padding(16.dp)
            .clickable(onClick = {
                context.startActivity(Intent(context, SecondActivity::class.java))
                Toast.makeText(context, "asdas", Toast.LENGTH_SHORT).show()
            })
    ) {
        Surface(
            modifier = Modifier.preferredSize(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            val imageModifier = remember {
                Modifier.preferredHeight(180.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
            }
            Image(
                imageResource(R.drawable.tom_boy),
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )

        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = Modifier.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.toIntPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
    }
}

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each children
            measurable.measure(constraints)
        }

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Track the y co-ord we have placed children up to
            var yPosition = 0

            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}