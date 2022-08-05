package com.example.testfornetologia.di.mainActivtiy



import com.example.testfornetologia.di.HomeFragmentScope
import com.example.testfornetologia.presentation.homeFragment.HomeFragment
import dagger.Component

@HomeFragmentScope
@Component(
    dependencies = [MainActvitityComponent::class],
)
interface HomeFragmentComponent {
    fun inject(fragment: HomeFragment)
    @Component.Factory
    interface Factory {
        fun create(applicationComponent: MainActvitityComponent): HomeFragmentComponent
    }
}
