package com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel
data class MeBindingModel(
        val id: String,
        val displayName: String,
        val username: String,
        val avatar: String?,
        val followingCount: String,
        val followerCount: String,
)
