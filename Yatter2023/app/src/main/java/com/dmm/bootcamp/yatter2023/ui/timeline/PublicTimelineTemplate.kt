package com.dmm.bootcamp.yatter2023.ui.timeline


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.StatusBindingModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.dmm.bootcamp.yatter2023.domain.service.CheckLoginService
import com.dmm.bootcamp.yatter2023.infra.pref.MePreferences
import com.dmm.bootcamp.yatter2023.ui.login.LoginActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PublicTimelineTemplate(
    statusList: List<StatusBindingModel>,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onClickPost: ()-> Unit,
    onClickJumpLogin: () -> Unit,

) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

Scaffold(
    scaffoldState = scaffoldState,
    drawerContent = {
                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)) {
                        TextButton(
                            onClick = {
                                      onClickJumpLogin()
                            },

                            ) {
                            Text(text = "ログアウト")
                        }
                    }
    },
    topBar = {
            TopAppBar(
                title = {Text(text = "タイムライン")},
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.List, contentDescription = "ドロワーボタン")
                    }
                }
            )

    },
    floatingActionButton = {
        FloatingActionButton(onClick = onClickPost) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "post")
        }
    }
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
            .padding(it),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(statusList) { item ->
                StatusRow(statusBindingModel = item)
            }
        }
        PullRefreshIndicator(
            isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
        if (isLoading) {
            CircularProgressIndicator()
        }
    }
}
}



@Preview
@Composable
private fun PublicTimelineTemplatePreview() {
    Yatter2023Theme {
        Surface {
            PublicTimelineTemplate(
                statusList = listOf(
                    StatusBindingModel(
                        id = "id",
                        displayName = "display name",
                        username = "username",
                        avatar = null,
                        content = "preview content",
                        attachmentMediaList = listOf()
                    ),
                    StatusBindingModel(
                        id = "me990928",
                        displayName = "nanana",
                        username = "me990928",
                        avatar = null,
                        content = "preview content",
                        attachmentMediaList = listOf()
                    )
                ),
                isLoading = false,
                isRefreshing = false,
                onRefresh = {},
                onClickPost = {},
                onClickJumpLogin = {}
            )
        }
    }
}