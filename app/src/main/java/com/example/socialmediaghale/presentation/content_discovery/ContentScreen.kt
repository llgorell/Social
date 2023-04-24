package com.example.socialmediaghale.presentation.content_discovery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.socialmediaghale.R
import com.example.socialmediaghale.presentation.content_discovery.utils.ContentItem
import com.example.socialmediaghale.presentation.util.Screens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ContentScreen(navController: NavController, viewModel: ContentViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val pagerState = rememberPagerState()
    val scrollabe = rememberScrollState()
    val list = listOf(
        "For You",
        "News",
        "Entertainment",
        "Lifestyle",
        "Startup",
        "Travel",
        "Poetry",
        "Entrepreneurship",
        "Education",
        "Health",
        "Social Media"
    )
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = { navController.navigate(Screens.ProfileScreen.route) }) {
                Image(
                    painter = painterResource(id = R.drawable.dots),
                    contentDescription = "dots_menu"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.category),
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "back")
            }

        }

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(rememberScrollState(), orientation = Orientation.Horizontal)
                .height(64.dp),
            contentColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .padding(bottom = 14.dp),
                    height = 2.dp,
                    color = colorResource(id = R.color.blue)

                )
            },
            divider = {
                TabRowDefaults.Divider(
                    thickness = 14.dp, modifier = Modifier
                        .padding(horizontal = 24.dp),
                    color = colorResource(
                        id = R.color.grayspec
                    )
                )
            },
            edgePadding = 24.dp

        ) {

            list.forEachIndexed { tabIndex, title ->
                Tab(
                    selected = selectedTabIndex == tabIndex,
                    onClick = {
                        selectedTabIndex = tabIndex
                        viewModel.onEvent(ContentEvent.OnFilterClick(title))
                    },
                    text = {
                        Text(
                            text =
                            title,
                            color = if (selectedTabIndex == tabIndex) colorResource(id = R.color.blue) else Color.Black,
                            style = MaterialTheme.typography.button,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 32.dp)
                        )
                    }
                )
            }

        }

        LazyColumn(
            Modifier
                .fillMaxSize()
                .scrollable(scrollabe, orientation = Orientation.Vertical)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(state.list.value) {
                ContentItem(it)
            }
        }

    }

}
