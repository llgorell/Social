package com.example.socialmediaghale.presentation.content_discovery

sealed class ContentEvent {

    data class OnFilterClick(val category: String) : ContentEvent()
}