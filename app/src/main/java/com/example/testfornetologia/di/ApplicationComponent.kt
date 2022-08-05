package com.example.testfornetologia.di

import com.example.testfornetologia.BuildConfig
import com.example.testfornetologia.data.network.api.NetworkService
import dagger.*
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


@Module()
class ApplicationModule {
    @ApplicationScope
    @Provides
    fun provideNetworkService(
    ): NetworkService {
        val retrofit = Retrofit.Builder()
            .client(getRetrofitClient())
            .baseUrl("https://raw.githubusercontent.com/netology-code/rn-task/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create()
    }
    private fun getRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder().
            addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().build())
            }.also { client ->
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()
    }
}

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun provideNetworkService(): NetworkService

    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }
}
