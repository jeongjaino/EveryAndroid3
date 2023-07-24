package com.jaino.pagingexample.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.jaino.pagingexample.data.local.database.BeerDatabase
import com.jaino.pagingexample.data.local.database.BeerRemoteMediator
import com.jaino.pagingexample.data.local.model.BeerEntity
import com.jaino.pagingexample.data.remote.service.BeerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(
        @ApplicationContext context: Context
    ): BeerDatabase {
        return Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            "beers_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerService(): BeerService{
        return Retrofit.Builder()
            .baseUrl(BeerService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideBeerPager(
        beerDatabase: BeerDatabase, beerService: BeerService
    ): Pager<Int, BeerEntity>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                beerDatabase, beerService
            ),
            pagingSourceFactory = {
                beerDatabase.dao.pagingSource()
            }
        )
    }
}