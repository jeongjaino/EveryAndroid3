package com.jaino.pagingexample.data.remote.service

import com.jaino.pagingexample.data.remote.model.BeerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerService {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<BeerResponse>

    companion object{
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}