package dev.hirogakatageri.github.demo.domain.users

import dev.hirogakatageri.github.demo.data.service.GithubService

class UsersRepository(
    private val githubService: GithubService
) {
    suspend fun searchUsers(query: String) = githubService.searchUsers(query)
}