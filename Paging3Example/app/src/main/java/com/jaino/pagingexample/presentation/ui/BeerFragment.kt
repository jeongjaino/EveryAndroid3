package com.jaino.pagingexample.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaino.pagingexample.R
import com.jaino.pagingexample.databinding.FragmentBeerBinding
import com.jaino.pagingexample.presentation.adapter.BeerAdapter
import com.jaino.pagingexample.presentation.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BeerFragment: Fragment() {

    private var _binding : FragmentBeerBinding? = null
    private val binding get() = requireNotNull(_binding){ "binding object is not initialized"}

    private val viewModel : BeerViewModel by viewModels()
    private lateinit var adapter : BeerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beer, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeData()
    }

    private fun initAdapter(){
        adapter = BeerAdapter()
        binding.beerRecyclerView.adapter = adapter
        binding.beerRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeData(){
        viewModel.beerPagingFlow.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                adapter.submitData(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}