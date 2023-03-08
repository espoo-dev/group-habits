package com.group.so.data.entities.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Customer(
    val id: Int,
    val name: String,
    val documentNumber: String,
    val phone: String,
    val stateInscription: String?,
    val customerType: String,
) : Parcelable
