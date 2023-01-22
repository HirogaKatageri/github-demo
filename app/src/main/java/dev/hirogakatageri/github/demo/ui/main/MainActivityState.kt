package dev.hirogakatageri.github.demo.ui.main

import dev.hirogakatageri.github.demo.data.user.UserRemoteData

sealed interface MainActivityState {

    class Ready(val data: List<UserRemoteData>) : MainActivityState
    object Searching : MainActivityState
    object Error : MainActivityState

}