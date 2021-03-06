package com.example.testandroidcompose.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testandroidcompose.R
import com.example.testandroidcompose.ui.AoenColors
import com.example.testandroidcompose.ui.AoenTypography
import com.example.testandroidcompose.ui.TestAndroidComposeTheme
import com.example.testandroidcompose.ui.base.AoenButtonText
import com.example.testandroidcompose.ui.ui_test.fadeIn

@Composable
fun WelcomeScreen(modifier: Modifier) {
    Box {
        HeaderImage(modifier = modifier)
        SelectFlow(modifier = modifier)
    }
}

@Composable
fun HeaderImage(modifier: Modifier) {
//    modifier.preferredHeight(50.dp)
    ConstraintLayout(modifier = modifier.fillMaxWidth().padding(top = 20.dp).fadeIn()) {
        val (logo, logoMember) = createRefs()
        Image(
            bitmap = imageResource(id = R.drawable.logo_white),
            modifier = Modifier.height(50.dp)
                .width(100.dp)
                .constrainAs(logo) {
                    start.linkTo(parent.start, 5.dp)
                },
            contentScale = ContentScale.Inside
        )

        Image(
            bitmap = imageResource(id = R.drawable.logo_member),
            modifier = Modifier.height(50.dp)
                .width(100.dp)
                .constrainAs(logoMember) {
                    end.linkTo(parent.end, 5.dp)
                },
            contentScale = ContentScale.Inside
        )
    }
}

@Composable
fun SelectFlow(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxHeight().padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                bitmap = imageResource(id = R.drawable.ic_language_login),
                modifier = Modifier.size(15.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = "Ng??n ng???",
                style = AoenTypography.subtitle2,
                fontSize = 14.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            backgroundColor = AoenColors.primary,
            modifier = Modifier.fillMaxWidth().fadeIn(), elevation = 5.dp
        ) {
            Column(modifier = modifier.padding(10.dp)) {
                AoenButtonText("????ng nh???p") {

                }
                Spacer(modifier = Modifier.height(5.dp))
                AoenButtonText("????ng k?? th??nh vi??n m???i") {

                }
                Spacer(modifier = Modifier.height(5.dp))
                AoenButtonText("B???n ???? c?? th??? th??nh vi??n") {

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSelectFlow() {
    TestAndroidComposeTheme {
        SelectFlow()
    }
}
