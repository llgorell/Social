package com.example.socialmediaghale.presentation.category.util

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.socialmediaghale.R

@Composable
fun CategoryItem(
    title: String,
    icon: ImageVector,
    selected: MutableState<Boolean>,
    onClick: () -> Unit,
) {


    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (selected.value) {
                colorResource(id = R.color.orange)
            } else {
                colorResource(id = R.color.grayspec)
            }
        ),
        shape = RoundedCornerShape(24.dp),
    ) {
        Icon(imageVector = icon, contentDescription = "business")
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            color = Color.Black,
            maxLines = 1,
            style = MaterialTheme.typography.button
        )
    }

}