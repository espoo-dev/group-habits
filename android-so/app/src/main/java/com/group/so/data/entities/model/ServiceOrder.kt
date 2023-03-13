@file:Suppress("LongParameterList")

package com.group.so.data.entities.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
class ServiceOrder(
    val id: Int,
    val conclusionDate: String?,
    val creationDate: String?,
    val customer: @RawValue Customer,
    val discount: Double,
    val extraInfo: String,
    val items: @RawValue List<Item>,
    val status: String
) : Parcelable
