package com.group.so.data.entities.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SalesUnit(
    val id: Int,
    val name: String,
) : Parcelable
