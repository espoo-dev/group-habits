package com.group.so.data.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.group.so.data.entities.model.Customer
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "customer")
data class CustomerDb(
    @PrimaryKey
    val id: Int,
    val name: String,
    val documentNumber: String,
    val phone: String,
    val stateInscription: String?,
    val customerType: String,

) {

    fun toModel(): Customer = Customer(
        id = id,
        name = name,
        documentNumber = documentNumber,
        phone = phone,
        stateInscription = stateInscription ?: "",
        customerType = customerType
    )
}

fun List<CustomerDb>.toModel(): List<Customer> =
    this.map {
        it.toModel()
    }
