package com.jaino.pagingexample.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jaino.pagingexample.data.local.model.BeerEntity

@Dao
interface BeerDao {
    @Upsert
    suspend fun upsertBeers(beers: List<BeerEntity>)

    @Query("SELECT * FROM beerentity")
    fun pagingSource(): PagingSource<Int, BeerEntity>

    @Query("DELETE FROM beerentity")
    fun deleteAllBeers()
}