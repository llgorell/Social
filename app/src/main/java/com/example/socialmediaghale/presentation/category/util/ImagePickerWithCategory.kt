package com.example.socialmediaghale.presentation.category.util

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

class ImagePickerWithCategory {

    companion object {
        fun changeCatToIcon(category: String): ImageVector {

            when (category) {
                "News" -> {
                    return Icons.Filled.LiveTv
                }
                "Entertainment" -> {
                    return Icons.Filled.LocationCity
                }
                "Lifestyle" -> {
                    return Icons.Filled.Style
                }
                "Startup" -> {
                    return Icons.Filled.NewLabel
                }
                "Travel" -> {
                    return Icons.Filled.FlightTakeoff
                }
                "Poetry" -> {
                    return Icons.Filled.Book
                }
                "Entrepreneurship" -> {
                    return Icons.Filled.DataArray
                }
                "Education" -> {
                    return Icons.Filled.AssuredWorkload
                }
                "Health" -> {
                    return Icons.Filled.LocalHospital
                }
                "Social Media" -> {
                    return Icons.Filled.SocialDistance
                }
            }
            return Icons.Filled.NewLabel
        }
    }

}