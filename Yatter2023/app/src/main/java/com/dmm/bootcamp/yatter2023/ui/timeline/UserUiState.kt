package com.dmm.bootcamp.yatter2023.ui.timeline

import com.dmm.bootcamp.yatter2023.ui.post.PostBindingModel
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.MeBindingModel


data class UserUiState(
    val bindingModel: MeBindingModel,
) {
    companion object {
        fun empty(): UserUiState = UserUiState(
            bindingModel = MeBindingModel(
                id = "",
                displayName = "",
                username = "",
                avatar = "",
                followingCount = "0",
                followerCount = "0"
            )
        )
    }
}