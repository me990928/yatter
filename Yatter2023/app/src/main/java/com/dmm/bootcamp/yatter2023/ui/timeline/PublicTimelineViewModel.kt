package com.dmm.bootcamp.yatter2023.ui.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2023.domain.repository.StatusRepository
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.converter.StatusConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PublicTimelineViewModel(private val statusRepository: StatusRepository,): ViewModel() {
    // TODO
    private val _uiState: MutableStateFlow<PublicTimelineUiState> =
        MutableStateFlow(PublicTimelineUiState.empty())
    val uiState: StateFlow<PublicTimelineUiState> = _uiState

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
}