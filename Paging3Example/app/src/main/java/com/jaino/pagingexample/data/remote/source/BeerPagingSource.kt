package com.jaino.pagingexample.data.remote.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jaino.pagingexample.data.local.model.BeerEntity
import com.jaino.pagingexample.data.remote.service.BeerService
import java.lang.Exception
import javax.inject.Inject

class BeerPagingSource @Inject constructor(
    private val service: BeerService
): PagingSource<Int, BeerEntity>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerEntity> {
        return try{
            val loadKey = params.key ?: 1
            val response = service.getBeers(page = loadKey, pageCount = params.loadSize)
            val nextKey = if(response.size < params.loadSize){
                null
            } else {
                loadKey + 1
            }
            LoadResult.Page(
                data = response.map{ it.toBeerEntity() },
                prevKey = null,
                nextKey = nextKey
            )
        } catch(e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, BeerEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}