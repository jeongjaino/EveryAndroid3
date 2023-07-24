package com.jaino.pagingexample.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jaino.pagingexample.data.local.dao.BeerDao
import com.jaino.pagingexample.data.local.model.BeerEntity

@Database(
    entities = [BeerEntity::class],
    version = 1
)
abstract class BeerDatabase: RoomDatabase() {
    abstract val dao : BeerDao
}