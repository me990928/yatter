package com.dmm.bootcamp.yatter2023.ui.timeline

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.MeBindingModel
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.MediaBindingModel
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.StatusBindingModel

@Composable
fun ProfileView(
    meBindingModel: MeBindingModel,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            modifier = modifier
                .size(50.dp)
                .padding(end = 10.dp)
                .clip(RoundedCornerShape(8.dp))
                .aspectRatio(1f),
            model = meBindingModel.avatar,
            contentDescription = "アバター画像",
            contentScale = ContentScale.Crop,
            )
        Text(text = meBindingModel.displayName)

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(text = "@${meBindingModel.username}")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "${meBindingModel.followingCount} フォロー　")
            }


            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "${meBindingModel.followerCount} フォロワー")
            }
        }
    }
}

@Preview
@Composable
private fun ProfileViewPreview() {
    Yatter2023Theme {
        Surface {
            ProfileView(
                meBindingModel = MeBindingModel(
                    id = "0",
                    displayName = "Yuya",
                    username = "me990928",
                    avatar = "https://avatars.githubusercontent.com/u/19385268?v=4",
                    followerCount = "10",
                    followingCount = "20",
                )
            )
        }
    }
}