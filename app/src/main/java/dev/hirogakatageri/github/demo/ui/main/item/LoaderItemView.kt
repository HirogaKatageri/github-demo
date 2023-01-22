package dev.hirogakatageri.github.demo.ui.main.item

import com.airbnb.epoxy.EpoxyModelClass
import dev.hirogakatageri.github.demo.R
import dev.hirogakatageri.github.demo.databinding.ItemLoaderBinding
import dev.hirogakatageri.github.demo.ui.helper.ViewBindingEpoxyModelWithHolder

@EpoxyModelClass
abstract class LoaderItemView : ViewBindingEpoxyModelWithHolder<ItemLoaderBinding>() {

    override fun ItemLoaderBinding.bind() {}

    override fun getDefaultLayout(): Int = R.layout.item_loader
}