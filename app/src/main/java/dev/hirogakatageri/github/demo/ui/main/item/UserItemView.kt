package dev.hirogakatageri.github.demo.ui.main.item

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import dev.hirogakatageri.github.demo.R
import dev.hirogakatageri.github.demo.data.user.UserRemoteData
import dev.hirogakatageri.github.demo.databinding.ItemUserBinding
import dev.hirogakatageri.github.demo.ui.helper.ViewBindingEpoxyModelWithHolder

@EpoxyModelClass
abstract class UserItemView : ViewBindingEpoxyModelWithHolder<ItemUserBinding>() {

    @EpoxyAttribute
    lateinit var data: UserRemoteData

    override fun ItemUserBinding.bind() {
        txtUser.text = data.login ?: "Username missing..."
    }

    override fun getDefaultLayout(): Int = R.layout.item_user
}