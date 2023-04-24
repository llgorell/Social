package com.example.socialmediaghale.data.repository.content

import com.example.socialmediaghale.data.remote.SocialApi
import com.example.socialmediaghale.data.remote.dto.Like
import com.example.socialmediaghale.domain.repository.content.LikeContentRepo
import javax.inject.Inject

class LikeContentRepoImpl @Inject constructor(private val api: SocialApi) : LikeContentRepo {
    override suspend fun likeContent(id: String): Like {
        return api.likeContent(id)
    }
}