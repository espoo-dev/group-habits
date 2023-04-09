package com.group.so.data.entities.model

import android.os.Parcelable
import com.group.so.core.presentation.components.autocomplete.AutoCompleteEntity
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class SalesUnit(
    val id: Int,
    val name: String,
) : AutoCompleteEntity, Parcelable {
    override fun filter(query: String): Boolean {
        return name.lowercase(Locale.getDefault())
            .startsWith(query.lowercase(Locale.getDefault()))
    }
}
