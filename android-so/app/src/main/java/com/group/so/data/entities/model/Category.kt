package com.group.so.data.entities.model

import com.group.so.core.presentation.components.autocomplete.AutoCompleteEntity
import java.util.Locale

class Category(
    val id: Int,
    val name: String,
) : AutoCompleteEntity {
    override fun filter(query: String): Boolean {
        return name.lowercase(Locale.getDefault())
            .startsWith(query.lowercase(Locale.getDefault()))
    }
}
