package com.example.testfornetologia.presentation.homeFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testfornetologia.R
import com.example.testfornetologia.data.network.model.Json
import com.example.testfornetologia.databinding.FragmentHomeBinding
import com.example.testfornetologia.di.mainActivtiy.DaggerHomeFragmentComponent
import com.example.testfornetologia.di.mainActivtiy.HomeFragmentComponent
import com.example.testfornetologia.presentation.homeFragment.adapter.DataListAdapter
import com.example.testfornetologia.presentation.mainActivity.MainActivity
import com.example.testfornetologia.utils.ErrorApp
import com.example.testfornetologia.utils.ResponseServer
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var homeFragmentComponent: HomeFragmentComponent
    private val binding: FragmentHomeBinding by viewBinding()

    @Inject
    lateinit var factory: FactoryHomeFragmentViewModel
    private val viewModelHome by viewModels<HomeFragmentViewModel> { factory }
    private lateinit var adapter: DataListAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeFragmentComponent = DaggerHomeFragmentComponent.factory()
            .create((requireActivity() as MainActivity).activityComponent)
        homeFragmentComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initFlowDataFromServer()
        initFlowError()
    }

    private fun initFlowDataFromServer() {
        lifecycleScope.launchWhenResumed {
            viewModelHome.responseNetworkStateFlow.collect {
                when (it) {
                    is ResponseServer.Success -> {
                        adapter.update(it.value.data)
                    }
                    is ResponseServer.Loading -> {
                        showLoading()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun initFlowError() {
        lifecycleScope.launchWhenResumed {
            viewModelHome.sharedStateFlowError.collect {
                when (it) {
                    is ErrorApp.FailureNetwork -> {
                        Toast.makeText(
                            context,
                            getString(R.string.server_error, it.errorBody.toString(),it.errorCode.toString()),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is ErrorApp.FailureUnknown -> {
                        Toast.makeText(
                            context,
                            getString(R.string.unknown_error, it.value.toString()),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is ErrorApp.FailureEnternet ->{
                        Toast.makeText(
                            context,
                            getString(R.string.network_error),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = DataListAdapter {
            Toast.makeText(
                context,
                "элемент ${it.direction.title}",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.rvData.adapter = adapter
        binding.rvData.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
    }



    private fun showLoading() {

    }


}