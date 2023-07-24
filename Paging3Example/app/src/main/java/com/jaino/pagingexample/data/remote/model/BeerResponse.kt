package com.jaino.pagingexample.data.remote.model

import com.jaino.pagingexample.data.local.model.BeerEntity

data class BeerResponse(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val first_brewed: String,
    val image_url: String?
){
    fun toBeerEntity() = BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        first_brewed = first_brewed,
        image_url = image_url
    )
}