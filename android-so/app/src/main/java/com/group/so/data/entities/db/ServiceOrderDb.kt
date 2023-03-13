package com.group.so.data.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.group.so.data.entities.model.ServiceOrder
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
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
    val items: List<ItemDb>? = emptyList(),
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

class ItemsDbConverters {
    private val moshi = Moshi.Builder().build()
    private val itemsType = Types.newParameterizedType(List::class.java, ItemDb::class.java)
    private val itemsAdapter = moshi.adapter<List<ItemDb>>(itemsType)

    @TypeConverter
    fun stringToItems(string: String): List<ItemDb> {
        return itemsAdapter.fromJson(string).orEmpty()
    }

    @TypeConverter
    fun itemsToString(items: List<ItemDb>): String {
        return itemsAdapter.toJson(items)
    }
}
