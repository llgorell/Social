package com.example.socialmediaghale.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.socialmediaghale.R
import com.example.socialmediaghale.presentation.util.Screens
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {

    var dialogOpen by remember {
        mutableStateOf(false)
    }



    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back button.
                // If you want to disable that functionality, simply leave this block empty.
                dialogOpen = false
            },
            confirmButton = {
                TextButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.blue)),
                    onClick = {
                        // perform the confirm action and
                        // close the dialog
                        viewModel.onEvent(ProfileEvent.SighOut)
                        dialogOpen = false
                        navController.navigate(Screens.SplashScreen.route)

                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_out),
                        color = Color.Blue,
                        style = MaterialTheme.typography.button
                    )
                }
            },
            dismissButton = {
                TextButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                    onClick = {
                        // close the dialog
                        dialogOpen = false
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        color = Color.White,
                        style = MaterialTheme.typography.button
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(id = R.string.sign_out_app),
                    style = MaterialTheme.typography.subtitle1
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.would_like),
                    style = MaterialTheme.typography.overline
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = Color.White
        )
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.grayspec)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = "back")
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.profile),
                style = MaterialTheme.typography.button
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = "back")
            }

        }

        Image(
            imageVector = Icons.Default.Person, contentDescription = "picture_profile",
            modifier = Modifier
                .border(
                    width = 4.dp, color = Color.White,
                    shape = RoundedCornerShape(50.dp)
                )
                .size(96.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.name),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = "@" + stringResource(id = R.string.mail),
            style = MaterialTheme.typography.subtitle1,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            modifier = Modifier.padding(horizontal = 24.dp),
            color = colorResource(id = R.color.grayspec)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

            Column(Modifier.wrapContentSize()) {
                Text(text = stringResource(id = R.string.number_left))
                Text(text = stringResource(id = R.string.scans))
            }

            Column(Modifier.wrapContentSize()) {
                Text(text = stringResource(id = R.string.number_right))
                Text(text = stringResource(id = R.string.topics))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(64.dp))
                .background(color = MaterialTheme.colors.background)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .shadow(shape = RoundedCornerShape(16.dp), elevation = 1.dp)
                        .background(color = colorResource(id = R.color.grayspec))
                ) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "setting")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.setting),
                    style = MaterialTheme.typography.button
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "arrow")
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .shadow(shape = RoundedCornerShape(16.dp), elevation = 1.dp)
                        .background(color = colorResource(id = R.color.grayspec))
                ) {
                    Icon(
                        imageVector = Icons.Default.IndeterminateCheckBox,
                        contentDescription = "billing"
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.billing_details),
                    style = MaterialTheme.typography.button
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "arrow")
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .shadow(shape = RoundedCornerShape(16.dp), elevation = 1.dp)
                        .background(color = colorResource(id = R.color.grayspec))
                ) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "perofile")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.profile),
                    style = MaterialTheme.typography.button
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "arrow")
                }

            }
            Spacer(modifier = Modifier.height(12.dp))
            Divider(
                Modifier.padding(horizontal = 32.dp),
                color = colorResource(id = R.color.grayspec)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .shadow(shape = RoundedCornerShape(16.dp), elevation = 1.dp)
                        .background(color = colorResource(id = R.color.grayspec))
                ) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "info")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.information),
                    style = MaterialTheme.typography.button
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "arrow")
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        dialogOpen = true

                    }
                    .padding(start = 24.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .shadow(shape = RoundedCornerShape(16.dp), elevation = 1.dp)
                        .background(color = colorResource(id = R.color.grayspec))
                ) {
                    Icon(imageVector = Icons.Default.Forward, contentDescription = "forward")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.log_out),
                    style = MaterialTheme.typography.button
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = {
                }) {
                    Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "arrow")
                }

            }
        }


    }
}