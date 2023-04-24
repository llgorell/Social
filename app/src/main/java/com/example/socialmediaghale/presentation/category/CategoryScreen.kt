package com.example.socialmediaghale.presentation.category

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.socialmediaghale.R
import com.example.socialmediaghale.presentation.category.util.CategoryItem
import com.example.socialmediaghale.presentation.category.util.ImagePickerWithCategory
import com.example.socialmediaghale.presentation.sign_in.SignInViewModel
import com.example.socialmediaghale.presentation.util.Screens
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CategoryScreen(navController: NavController, viewModel: CategoryViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels.dp
    val columnWidth = if (screenWidth < 600.dp) 120.dp else 180.dp
    val context = LocalContext.current
    val scrollableState = rememberScrollState()
    val selected = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {
        viewModel.eventSharedFlow.collectLatest { event ->
            when (event) {
                is CategoryViewModel.UiEvent.ShowSnackBar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    Log.d("kaveh", "error continue")
                }
                is CategoryViewModel.UiEvent.GoContinue -> {
                    Log.d("kaveh", "done")
                    navController.navigate(Screens.ContentScreen.route)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(state = scrollableState, orientation = Orientation.Vertical)
            .background(color = MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedButton(
            onClick = {},
            modifier = Modifier.size(50.dp),
            enabled = false, //avoid the oval shape
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.LightGray),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.onBackground)
        ) {
            Text(text = stringResource(id = R.string.two))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = stringResource(id = R.string.choos))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = stringResource(id = R.string.at_least))

        Spacer(modifier = Modifier.height(32.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.orange)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Palette, contentDescription = "art")
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.art),
                    color = Color.Black,
                    style = MaterialTheme.typography.button
                )
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.grayspec)),
                shape = RoundedCornerShape(24.dp),
            ) {
                Icon(imageVector = Icons.Default.BusinessCenter, contentDescription = "business")
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.business),
                    color = Color.Black,
                    style = MaterialTheme.typography.button
                )
            }


        }
        Spacer(modifier = Modifier.height(56.dp))


        LazyVerticalGrid(
            columns = GridCells.Adaptive(columnWidth),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(6.dp)
        ) {

            state.mockData?.let { list ->
                list.forEachIndexed { index, s ->
                    item() {
                        CategoryItem(
                            title = s.category,
                            icon = ImagePickerWithCategory.changeCatToIcon(s.category),
                            onClick = {
                                s.selected.value = !s.selected.value
                                Log.d("kaveh", "${s.selected}")
                                viewModel.onEvent(CategoryEvent.OnClickCategory(s.category))
                            },
                            selected = s.selected
                        )
                    }
                }
            }


        }
        Button(
            onClick = {
                viewModel.onEvent(CategoryEvent.Continue)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (viewModel.list.size < 5) Color.White else colorResource(
                    id = R.color.orange
                )
            ),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.count),
                style = MaterialTheme.typography.button,
                color = if (viewModel.list.size < 5) Color.LightGray else Color.Black,
                modifier = Modifier.padding(6.dp)

            )
        }
    }
}
