package dev.hirogakatageri.github.demo

import android.app.Application
import dev.hirogakatageri.github.demo.data.ServiceProvider
import dev.hirogakatageri.github.demo.data.service.GithubService
import dev.hirogakatageri.github.demo.domain.user.UsersRepository
import dev.hirogakatageri.github.demo.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApp : Application() {

    private val appModule = module {
        single<ServiceProvider> { ServiceProvider() }
        single<GithubService> { get<ServiceProvider>().githubService }
        single<UsersRepository> { UsersRepository(get()) }

        viewModel { MainViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApp)
            modules(appModule)
        }
    }
}
