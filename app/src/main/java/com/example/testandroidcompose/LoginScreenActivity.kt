package com.example.testandroidcompose

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.fragment.app.FragmentManager
import com.example.testandroidcompose.ui.AoenTheme
import com.example.testandroidcompose.ui.PinkMain
import com.example.testandroidcompose.ui.base.AmbientSystemUiController
import com.example.testandroidcompose.ui.base.SystemUiController
import com.example.testandroidcompose.ui.login.WelcomeScreen
import com.example.testandroidcompose.ui.ui_test.*

@ExperimentalAnimationApi
class LoginScreenActivity : AppCompatActivity() {

    private val imageModifier = Modifier.fillMaxHeight().fillMaxWidth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableFullScreenMode()

        setContent {
            AoenTheme {
//                Image(
//                    imageResource(R.drawable.background_login),
//                    modifier = imageModifier,
//                    contentScale = ContentScale.Crop
//                )
//                WelcomeScreen(Modifier)
//                TestCanvas()
//                demoLayout()
//                TestWithFragment(supportFragmentManager)
//                BackHandler(
//                    enabled = true,
//                    backDispatcher = this.onBackPressedDispatcher,
//                    onBack = { Toast.makeText(this, "asdasd", Toast.LENGTH_SHORT).show() })
                MyCustomLayout()
            }
        }
    }

    private fun enableFullScreenMode() {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val attrib = window.attributes
            attrib.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }
}

@Composable
fun scaffoldWithSystemUI(window: Window) {
    ScrollableColumn {
        val systemUiController = remember { SystemUiController(window) }
        Providers(AmbientSystemUiController provides systemUiController) {
            val sysUiController = AmbientSystemUiController.current
            sysUiController.setSystemBarsColor(
                color = PinkMain
            )
            ScaffoldMoreFancy()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun demoAnimation() {
    ScrollableColumn {
        animateVisibility()
        expandHorizontally()
        expandInAnimatedVisibility()
        expandVerticallyAnimatedVisibility()
        fadeInAnimatedVisibility()
        fadeOutAnimatedVisibility()
        shrinkHorizontallyAnimatedVisibility()
        shrinkOutAnimatedVisibility()
        shrinkVerticallyAnimatedVisibility()
        slideInAnimatedVisibility()
        slideInHorizontallyAnimatedVisibility()
        slideInVerticallyAnimatedVisibility()
        slideOutAnimatedVisibility()
        slideOutHorizontally()
        slideOutVerticallyAnimatedVisibility()
        anotherAnimatedVisibility()
        animateContentSize()
        InfiniteAnimationDemo()
    }
}

@Composable
fun demoFoundation() {
    ScrollableColumn {
        saveStateScrolled()
        ViewGradiant()
        horizontalScrollGradientColor()
        progressSemantics()
        progressSemanticsWithoutSize()
//        verticalScrollGradientColor()
    }
}

@Composable
fun demoLayout() {
    ScrollableColumn {
        ColumnBox()
        ColumnBox2()
        RowWithSpacer()
        AbsoluteOffSet()
        AbsoluteOffSetClick()
        AbsolutePadding()
        AspectRatio()
    }
}

@Composable
fun managerFragment() {

}

@Composable
fun BackHandler(
    enabled: Boolean,
    backDispatcher: OnBackPressedDispatcher,
    onBack: () -> Unit
) {
    // Safely update the current `onBack` lambda when a new one is provided
    val currentOnBack by rememberUpdatedState(onBack)
    // Remember in Composition a back callback that calls the `onBack` lambda
    val backCallback = remember {
        // Always intercept back events. See the SideEffect for a more complete version
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBack()
            }
        }
    }

    // On every successful composition, update the callback with the `enabled` value
    // to tell `backCallback` whether back events should be intercepted or not
    SideEffect {
        backCallback.isEnabled = enabled
    }

    // If `backDispatcher` changes, dispose and reset the effect
    DisposableEffect(backDispatcher) {
        // Add callback to the backDispatcher
        backDispatcher.addCallback(backCallback)

        // When the effect leaves the Composition, remove the callback
        onDispose {
            backCallback.remove()
        }
    }
}

@Composable
fun TestWithFragment(supportFragmentManager: FragmentManager) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(android.R.id.content, ExampleFragment(), ExampleFragment().javaClass.name)
    transaction.commit()
}