package com.example.testfornetologia.di

import com.example.testfornetologia.data.network.api.NetworkService
import dagger.*
import retrofit2.Retrofit
import retrofit2.create


@Module()
class ApplicationModule {
    @ApplicationScope
    @Provides
    fun provideNetworkService(
    ): NetworkService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/netology-code/rn-task/master/")
            .build()
        return retrofit.create()
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
