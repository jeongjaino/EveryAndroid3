package com.jaino.pagingexample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.jaino.pagingexample.data.local.model.BeerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    private val pager: Pager<Int, BeerEntity>
): ViewModel() {

    val beerPagingFlow = pager
        .flow
        .map { pagingData -> pagingData.map{ it.toBeer() } }
        .cachedIn(viewModelScope)
}