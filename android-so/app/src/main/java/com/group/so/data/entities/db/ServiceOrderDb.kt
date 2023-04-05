@file:Suppress(
    "TooGenericExceptionCaught",

)

package com.group.so.data.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.group.so.data.entities.model.ServiceOrder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Entity(tableName = "service_order")
data class ServiceOrderDb(
    @PrimaryKey
    val id: Int,
    val conclusionDate: String?,
    val creationDate: String?,
    val customer: CustomerDb?,
    val discount: Double,
    val extraInfo: String,
    val items: List<ItemDb> = arrayListOf(),
    val status: String,
) {
    fun toModel(): ServiceOrder = ServiceOrder(
        id = id,
        conclusionDate = conclusionDate,
        creationDate = creationDate,
        customer = customer?.toModel()!!,
        discount = discount,
        extraInfo = extraInfo,
        items = emptyList(),
        status = status,
    )
}

fun List<ServiceOrderDb>.toModel(): List<ServiceOrder> =
    this.map {
        it.toModel()
    }

class CustomerTypeConverter {
    @TypeConverter
    fun fromString(string: String?): CustomerDb? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(CustomerDb::class.java)
        return jsonAdapter.fromJson(string)
    }

    @TypeConverter
    fun toString(array: CustomerDb?): String? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(CustomerDb::class.java)
        return jsonAdapter.toJson(array)
    }
}

object ItemsDbConverters {
    @TypeConverter
    fun stringToItems(string: String?): List<ItemDb>? {
        return try {
            Gson().fromJson<List<ItemDb>>(string ?: "") // using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    @TypeConverter
    fun itemsToString(items: List<ItemDb>?): String {
        return Gson().toJson(items!!)
    }
}

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)
