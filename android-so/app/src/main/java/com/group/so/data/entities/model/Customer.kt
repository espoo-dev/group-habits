package com.group.so.data.entities.model

import android.os.Parcelable
import com.group.so.core.presentation.components.autocomplete.AutoCompleteEntity
import kotlinx.parcelize.Parcelize
import java.util.Locale

@Parcelize
class Customer(
    val id: Int,
    val name: String,
    val documentNumber: String,
    val phone: String,
    val stateInscription: String?,
    val customerType: String,
) : Parcelable, AutoCompleteEntity {
    override fun filter(query: String): Boolean {
        return name.lowercase(Locale.getDefault())
            .startsWith(query.lowercase(Locale.getDefault()))
    }
}
