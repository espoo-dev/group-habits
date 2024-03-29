package com.group.so.presentation.ui.login.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class TextFieldState(
    private val validator: (String) -> Boolean = { true },
    private val errorMessage: (String) -> String
) {
    var text by mutableStateOf<String>("")
    var error by mutableStateOf<String?>(null)

    fun validate() {
        error = if (isValid()) {
            null
        } else {
            errorMessage(text)
        }
    }
    fun isValid() = validator(text)
}
