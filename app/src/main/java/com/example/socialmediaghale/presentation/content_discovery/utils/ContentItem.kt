package com.example.socialmediaghale.presentation.content_discovery.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.socialmediaghale.R
import com.example.socialmediaghale.data.remote.dto.CategoryContentItem
import kotlin.reflect.typeOf

@Composable
fun ContentItem(item: CategoryContentItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = colorResource(id = R.color.grayspec),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_item),
                contentDescription = "image",
                modifier = Modifier.size(122.dp),
                contentScale = ContentScale.FillBounds

            )
            Spacer(modifier = Modifier.width(24.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(top = 32.dp, bottom = 24.dp)
            ) {
                Text(text = item.category, style = MaterialTheme.typography.button)
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit ")
            }
        }

    }
}