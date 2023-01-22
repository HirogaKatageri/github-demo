package dev.hirogakatageri.github.demo.data.service

import dev.hirogakatageri.github.demo.data.helper.ItemsWrapper
import dev.hirogakatageri.github.demo.data.user.UserRemoteData
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): ItemsWrapper<UserRemoteData>

}