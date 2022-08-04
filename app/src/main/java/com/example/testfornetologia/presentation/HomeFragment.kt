package com.example.testfornetologia.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.testfornetologia.R
import com.example.testfornetologia.di.mainActivtiy.DaggerHomeFragmentComponent
import com.example.testfornetologia.di.mainActivtiy.HomeFragmentComponent

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var homeFragmentComponent: HomeFragmentComponent



    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeFragmentComponent = DaggerHomeFragmentComponent.factory()
            .create((requireActivity() as MainActivity).activityComponent)
        homeFragmentComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}