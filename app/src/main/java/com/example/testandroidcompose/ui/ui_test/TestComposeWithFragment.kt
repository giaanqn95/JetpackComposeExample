package com.example.testandroidcompose.ui.ui_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.testandroidcompose.R

class ExampleFragment : Fragment() {
    private val imageModifier = Modifier.fillMaxHeight().fillMaxWidth()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Image(
                        imageResource(R.drawable.background_login),
                        modifier = imageModifier,
                        contentScale = ContentScale.Crop
                    )
                    // In Compose world
                    ContentExample()
                    Text("Hello Compose!")
                }
            }
        }
    }
}

private val viewId = View.generateViewId()

@Composable
fun CustomViewWithAndroidView(supportFragmentManager: FragmentManager) {
    val selectedItem = remember { mutableStateOf(0) }
    val imageModifier = Modifier.fillMaxHeight().fillMaxWidth()
    // Adds view to Compose
    AndroidView(
        modifier = Modifier.fillMaxSize(), // Occupy the max size in the Compose UI tree
        viewBlock = { context ->
            // Creates custom view
            // Sets up listeners for View -> Compose communication
            FrameLayout(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                id = viewId
                val transaction = supportFragmentManager.beginTransaction()
                transaction.add(id, ExampleFragment(), ExampleFragment().javaClass.name)
                transaction.commit()
            }
//            ComposeView(context).apply {
//                layoutParams = ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//                )
//                setContent {
//                    Image(
//                        imageResource(R.drawable.background_login),
//                        modifier = imageModifier.size(50.dp),
//                        contentScale = ContentScale.Crop
//                    )
//                }
//            }
        },
        update = { view ->
            // View's been inflated or state read in this block has been updated
            // Add logic here if necessary

            // As selectedItem is read here, AndroidView will recompose
            // whenever the state changes
            // Example of Compose -> View communication

        }
    )
}

@Composable
fun ContentExample() {
    Column(Modifier.fillMaxSize()) {
        Text("Look at this CustomView!")
//        CustomViewWithAndroidView()
    }
}