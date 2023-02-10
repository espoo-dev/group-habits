package com.group.so.data.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.model.SalesUnit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Entity(tableName = "item")
data class ItemDb(
    @PrimaryKey
    val id: Int,
    val name: String,
    val extraInfo: String,
    val salePrice: String,
    val purchasePrice: String,
    val itemType: String,
    var category: Category? = null,
    var saleUnit: SalesUnit? = null,
) {
    fun toModel(): Item = Item(
        id = id,
        name = name,
        extraInfo = extraInfo,
        salePrice = salePrice,
        purchasePrice = purchasePrice,
        itemType = itemType,
        category = category,
        saleUnit = saleUnit
    )
}

fun List<ItemDb>.toModel(): List<Item> =
    this.map {
        it.toModel()
    }

class CategoryTypeConverter {
    @TypeConverter
    fun fromString(string: String): Category? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(Category::class.java)
        return jsonAdapter.fromJson(string)
    }

    @TypeConverter
    fun toString(array: Category): String? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(Category::class.java)
        return jsonAdapter.toJson(array)
    }
}

class SaleUnitTypeConverter {
    @TypeConverter
    fun fromString(string: String): SalesUnit? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(SalesUnit::class.java)
        return jsonAdapter.fromJson(string)
    }

    @TypeConverter
    fun toString(array: SalesUnit): String? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(SalesUnit::class.java)
        return jsonAdapter.toJson(array)
    }
}
