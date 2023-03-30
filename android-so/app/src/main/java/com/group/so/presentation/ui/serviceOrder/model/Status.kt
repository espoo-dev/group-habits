package com.group.so.presentation.ui.serviceOrder.model

import com.group.so.core.presentation.components.autocomplete.AutoCompleteEntity
import java.util.Locale

class Status (
    val name: String
    ): AutoCompleteEntity {
    override fun filter(query: String): Boolean {
        return name.lowercase(Locale.getDefault())
            .startsWith(query.lowercase(Locale.getDefault()))
    }
}