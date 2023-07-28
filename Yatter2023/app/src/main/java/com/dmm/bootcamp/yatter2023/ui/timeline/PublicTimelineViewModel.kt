package com.dmm.bootcamp.yatter2023.ui.timeline

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2023.domain.repository.StatusRepository
import com.dmm.bootcamp.yatter2023.domain.service.GetMeService
import com.dmm.bootcamp.yatter2023.domain.service.LogoutService
import com.dmm.bootcamp.yatter2023.infra.api.json.AccountJson
import com.dmm.bootcamp.yatter2023.infra.pref.MePreferences
import com.dmm.bootcamp.yatter2023.ui.post.PostUiState
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.converter.StatusConverter
import com.dmm.bootcamp.yatter2023.util.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PublicTimelineViewModel(
    private val statusRepository: StatusRepository,
    private val logoutService: LogoutService,
    private val getMeService: GetMeService,
    ): ViewModel() {
    // TODO
    private val _uiState: MutableStateFlow<PublicTimelineUiState> =
        MutableStateFlow(PublicTimelineUiState.empty())
    val uiState: StateFlow<PublicTimelineUiState> = _uiState


    private val _uiStateUser: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState.empty())
    val uiStateUser: StateFlow<UserUiState> = _uiStateUser

    private val _navigateToPost: SingleLiveEvent<Unit> = SingleLiveEvent()
    val navigateToPost: LiveData<Unit> = _navigateToPost

    private val _jumpToLoginPage: SingleLiveEvent<Unit> = SingleLiveEvent()
    val jumpToLoginPage: LiveData<Unit> = _jumpToLoginPage


    private suspend fun fetchPublicTimeline(){
        val statusList = statusRepository.findAllPublic() // 1
        _uiState.update {
            // `update` はラムダ式(JSでいうアロー関数)を受け取ります。引数名を指定しますが、これを省略すると `it` になります。
            // { state ->
            //  state.copy(...)
            // }
//            update 自体は更新前のStateの値をラムダ式の引数として渡します。そのため、この場合の it は呼び出したタイミングの _uiState の値になります。
//            例えば、次のように書くこともできます。
            it.copy(
                statusList = StatusConverter.convertToBindingModel(statusList), // 2
            )
        }

    }

    fun onCreate(){
        viewModelScope.launch {

            val me = getMeService.execute()

            val snapshotBindingModel = uiStateUser.value.bindingModel
            _uiStateUser.update {
                it.copy(
                    bindingModel = snapshotBindingModel.copy(
                        id = me?.id.toString(),
                        displayName = me?.displayName.toString(),
                        username = me?.displayUsername.toString(),
                        avatar = me?.avatar.toString(),
                        followingCount = me?.followingCount.toString(),
                        followerCount = me?.followerCount.toString(),
                    )
                )
            }
        }
    }

    // 画面を呼び出すたびに実行
    fun onResume() {
        viewModelScope.launch { // 1
            _uiState.update { it.copy(isLoading = true) } // 2
            fetchPublicTimeline() // 3
            _uiState.update { it.copy(isLoading = false) } // 4
        }
    }
    // スワイプリフレッシュ時に実行
    fun onRefresh() {
        viewModelScope.launch { // 1
            _uiState.update { it.copy(isRefreshing = true) } // 2
            fetchPublicTimeline() // 3
            _uiState.update { it.copy(isRefreshing = false) } // 4
        }
    }

    // 投稿ボタン
    fun onClickPost(){
        _navigateToPost.value = Unit
    }

    fun onClickLogin(){
        viewModelScope.launch {
            logoutService.execute()
            _jumpToLoginPage.value = Unit
        }
    }
}