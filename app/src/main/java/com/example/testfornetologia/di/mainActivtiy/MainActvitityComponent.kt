package com.example.testfornetologia.di.mainActivtiy


import com.example.testfornetologia.data.network.NetworkRepository
import com.example.testfornetologia.data.network.NetworkRepositoryImpl
import com.example.testfornetologia.di.ApplicationComponent
import com.example.testfornetologia.di.MainActivityScope
import com.example.testfornetologia.domain.GetDataFromServerUseCase
import com.example.testfornetologia.domain.GetDataFromServerUseCaseImpl
import com.example.testfornetologia.presentation.MainActivity
import dagger.Binds
import dagger.Component
import dagger.Module


@Module
interface MainModule {
    @Binds
    @Suppress("FunctionName")
    fun bindsNetworkRepository_to_NetworkRepositoryImpl(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository

    @Binds
    @Suppress("FunctionName")
    fun bindsGetDataFromServerUseCase_to_GetDataFromServerUseCaseImpl(getDataFromServerUseCaseImpl: GetDataFromServerUseCaseImpl): GetDataFromServerUseCase

}
@MainActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [MainModule::class]
)
interface MainActvitityComponent {

    @Suppress("FunctionName")
    fun bindsNetworkRepository_to_NetworkRepositoryImpl(): NetworkRepository

    @Suppress("FunctionName")
    fun bindsGetDataFromServerUseCase_to_GetDataFromServerUseCaseImpl(): GetDataFromServerUseCase

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(applicationComponent: ApplicationComponent): MainActvitityComponent
    }
}

