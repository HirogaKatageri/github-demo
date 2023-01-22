package dev.hirogakatageri.github.demo.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemsWrapper<T>(
    @Json(name = "items") val items: List<T>
)