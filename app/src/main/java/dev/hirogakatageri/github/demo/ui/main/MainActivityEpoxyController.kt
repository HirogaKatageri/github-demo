package dev.hirogakatageri.github.demo.ui.main

import com.airbnb.epoxy.EpoxyController
import dev.hirogakatageri.github.demo.data.user.UserRemoteData
import dev.hirogakatageri.github.demo.ui.user.userItemView

class MainActivityEpoxyController : EpoxyController() {

    private val _users: MutableList<UserRemoteData> = mutableListOf()

    fun setUsers(users: List<UserRemoteData>) {
        _users.clear()
        _users.addAll(users)
    }

    override fun buildModels() {
        _users.forEach { user ->
            userItemView {
                id(user.id)
                data(user)
            }
        }
    }
}