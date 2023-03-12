@file:Suppress("LongParameterList")

package com.group.so.data.entities.model

import android.os.Parcelable
import com.group.so.data.entities.network.CustomerDTO
import com.group.so.data.entities.network.ItemDTO
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
class ServiceOrder(
    val id: Int,
    val conclusionDate: String?,
    val creationDate: String?,
    val customer: @RawValue CustomerDTO,
    val discount: Double,
    val extraInfo: String,
    val items: @RawValue List<ItemDTO>,
    val status: String
) : Parcelable
