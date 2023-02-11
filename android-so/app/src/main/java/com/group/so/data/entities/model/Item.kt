@file:Suppress("LongParameterList")

package com.group.so.data.entities.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
class Item(
    val id: Int,
    val name: String,
    val extraInfo: String,
    val salePrice: Double,
    val purchasePrice: Double,
    val itemType: String,
    var category: @RawValue Category? = null,
    var saleUnit: @RawValue SalesUnit? = null,
) : Parcelable
