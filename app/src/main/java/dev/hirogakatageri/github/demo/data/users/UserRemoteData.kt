package dev.hirogakatageri.github.demo.data.users

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRemoteData(
    @Json(name = "id") val id: String,
    @Json(name = "login") val login: String? = null
)