package com.example.socialmediaghale.domain.repository.content

import com.example.socialmediaghale.data.remote.dto.Like

interface LikeContentRepo {

    suspend fun likeContent(id: String): Like
}