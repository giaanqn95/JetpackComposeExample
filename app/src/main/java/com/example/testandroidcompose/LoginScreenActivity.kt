package com.example.testandroidcompose

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import com.example.testandroidcompose.ui.base.AmbientSystemUiController
import com.example.testandroidcompose.ui.base.SystemUiController
import com.example.testandroidcompose.ui.ui_test.*

@ExperimentalAnimationApi
class LoginScreenActivity : AppCompatActivity() {

    private val imageModifier = Modifier.fillMaxHeight().fillMaxWidth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableFullScreenMode()
        setContent {
            ScrollableColumn {
                val systemUiController = remember { SystemUiController(window) }
                Providers(AmbientSystemUiController provides systemUiController) {
//                val sysUiController = AmbientSystemUiController.current
//                sysUiController.setSystemBarsColor(
//                    color = PinkMain
//                )
//                AoenTheme {
//                    Image(
//                        imageResource(R.drawable.background_login),
//                        modifier = imageModifier,
//                        contentScale = ContentScale.Crop
//                    )
//                    WelcomeScreen(Modifier)
//                }
//                ScaffoldMoreFancy()
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
                }
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
//        setDisplayCutout()
//        getStatusBarHeight(this)
    }
}