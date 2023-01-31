package com.group.so.core.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson
import org.jetbrains.annotations.Nullable

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class NullToEmptyString
class NullToEmptyStringAdapter {
    @ToJson
    fun toJson(@NullToEmptyString value: String?): String? {
        return value
    }
    @FromJson
    @NullToEmptyString
    fun fromJson(@Nullable data: String?): String? {
        return data ?: ""
    }
}
