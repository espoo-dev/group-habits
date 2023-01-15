package com.group.so.core.ui.components

import androidx.compose.runtime.Composable
import com.group.so.core.State
import com.group.so.data.entities.model.Category

@Composable
fun <T : Any> AsyncData(
    resultState: State<T>,
    loadingContent: @Composable () -> Unit = { GenericLoading() },
    errorContent: @Composable () -> Unit = { GenericError() },
    content: @Composable (data: T?) -> Unit
) {
    resultState.let { state ->
        when (state) {
            is State.Loading -> {
                loadingContent()
            }
            is State.Error -> {
                errorContent()
            }
            null -> {
                content(null)
            }
            is State.Idle -> {
                content(null)
            }
            is State.Success -> {
                content(state.result)
            }
        }
    }
}