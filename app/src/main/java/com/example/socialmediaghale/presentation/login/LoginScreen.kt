package com.example.socialmediaghale.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.socialmediaghale.R
import com.example.socialmediaghale.presentation.util.Screens


@Composable
fun LoginScreen(navController: NavController) {
    Box(
        contentAlignment = Alignment.BottomCenter, modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.login), contentScale = ContentScale.FillBounds)
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp), verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = {
                    navController.navigate(Screens.SignInScreen.route)
                }, shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text(
                    text = stringResource(id = R.string.log_in),
                    style = MaterialTheme.typography.h6
                )
            }
            Row(
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 48.dp, vertical = 4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.dont_have_account),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .wrapContentHeight()
                        .align(CenterVertically),
                    color = Color.White
                )
                TextButton(onClick = { /*TODO*/ }, contentPadding = PaddingValues(start = 0.dp)) {
                    Text(
                        text = stringResource(id = R.string.sign_up),
                        color = Color.White,
                        modifier = Modifier.background(Color.Transparent),
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }
    }

}