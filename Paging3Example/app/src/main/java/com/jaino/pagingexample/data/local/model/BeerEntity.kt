package com.jaino.pagingexample.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jaino.pagingexample.domain.model.Beer

@Entity
data class BeerEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val first_brewed: String,
    val image_url: String?
){
    fun toBeer() = Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        first_brewed = first_brewed,
        image_url = image_url
    )
}