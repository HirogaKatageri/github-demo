package dev.hirogakatageri.github.demo.data

import dev.hirogakatageri.github.demo.BuildConfig
import dev.hirogakatageri.github.demo.data.service.GithubService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ServiceProvider {

    private val okHttpClient: OkHttpClient get() = buildOkHttpClient()
    private val githubServiceInterceptor: Interceptor get() = GithubServiceInterceptor()
    private val retrofit: Retrofit get() = buildRetrofit()

    val githubService: GithubService get() = retrofit.create(GithubService::class.java)

    private fun buildOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(BODY))
        }

        builder.addInterceptor(githubServiceInterceptor)

        return builder.build()
    }

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    private class GithubServiceInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
                .newBuilder()
                .method(chain.request().method, chain.request().body)

            request.addHeader("X-GitHub-Api-Version", "2022-11-28")
            request.addHeader("Authorization", "Bearer ${BuildConfig.GITHUB_TOKEN}")

            return chain.proceed(request.build())
        }
    }
}