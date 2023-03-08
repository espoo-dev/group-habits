@file:Suppress(
    "TopLevelPropertyNaming",
    "LongMethod",
    "FunctionParameterNaming",
    "FunctionNaming",
    "LongParameterList"
)

package com.group.so.core.ui.components.autocomplete.utils

import com.group.so.core.ui.components.autocomplete.ValueAutoCompleteEntity

typealias CustomFilter<T> = (T, String) -> Boolean

fun <T> List<T>.asAutoCompleteEntities(filter: CustomFilter<T>): List<ValueAutoCompleteEntity<T>> {
    return map {
        object : ValueAutoCompleteEntity<T> {
            override val value: T = it

            override fun filter(query: String): Boolean {
                return filter(value, query)
            }
        }
    }
}

const val AutoCompleteSearchBarTag = "AutoCompleteSearchBar"
