package com.example.socialmediaghale.presentation.sign_in

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.socialmediaghale.R
import com.example.socialmediaghale.presentation.util.Screens
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.log


@Composable
fun SignInScreen(navController: NavHostController, viewModel: SignInViewModel = hiltViewModel()) {

    val email = viewModel.email.value
    val password = viewModel.password.value
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventSharedFlow.collectLatest { event ->
            when (event) {
                is SignInViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.message
                    )
                }
                is SignInViewModel.UiEvent.SaveToken -> {
                    Log.d("kaveh", "done")
                    navController.navigate(Screens.CategoryScreen.route)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .background(color = MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.round2),
            modifier = Modifier.size(120.dp),
            contentDescription = "logo"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.wellcome),
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(

            value = email.text,
            onValueChange = {
                viewModel.onEvent(OnSignInEvent.EnteredEmail(it))
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                disabledIndicatorColor = Color.Transparent,
                backgroundColor = MaterialTheme.colors.background
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            label = {
                if (email.isHintVisible) {
                    Text(text = stringResource(id = R.string.email), color = Color.Gray)
                }
            },
            shape = RoundedCornerShape(size = 8.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password.text,
            onValueChange = {
                viewModel.onEvent(OnSignInEvent.EnteredPassword(it))
            },
            colors = TextFieldDefaults.textFieldColors(
                disabledIndicatorColor = Color.Transparent,
                backgroundColor = MaterialTheme.colors.background
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            label = {
                if (password.isHintVisible) {
                    Text(text = stringResource(id = R.string.pass), color = Color.Gray)
                }
            },
            shape = RoundedCornerShape(size = 8.dp),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisibility)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(imageVector = image, contentDescription = "passwordIcon")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                viewModel.onEvent(OnSignInEvent.Login)
            }, shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
        ) {
            Text(
                text = stringResource(id = R.string.log_in),
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.or), color = Color.Gray,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Divider(Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
        ) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(48.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(width = 1.dp, color = Color.Gray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "facebook"
                )
            }
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(48.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(width = 1.dp, color = Color.Gray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "google"
                )
            }
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(48.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(width = 1.dp, color = Color.Gray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.apple),
                    contentDescription = "apple"
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp, vertical = 4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.dont_have_account),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .background(Color.Transparent)
                    .wrapContentHeight()
                    .align(
                        Alignment.CenterVertically
                    ),
                color = Color.DarkGray
            )
            TextButton(onClick = { /*TODO*/ }, contentPadding = PaddingValues(start = 0.dp)) {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    color = Color.Black,
                    modifier = Modifier.background(Color.Transparent),
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}