package com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.converter

import com.dmm.bootcamp.yatter2023.domain.model.Status
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.MeBindingModel

object MeConverter {
    fun convertToMeModel(statusList: List<Status>): List<MeBindingModel> =
        statusList.map { convertToMeModel(it) }

    fun convertToMeModel(status: Status): MeBindingModel = MeBindingModel(
        id = status.account.id.toString(),
        displayName = status.account.displayName.toString(),
        username = status.account.username.toString(),
        avatar = status.account.avatar.toString(),
        followingCount = status.account.followingCount.toString(),
        followerCount = status.account.followerCount.toString(),
    )
}