package com.example.socialmediaghale.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilterModel(
   @SerializedName("categories")
   @Expose
   val categories: List<String>
)
