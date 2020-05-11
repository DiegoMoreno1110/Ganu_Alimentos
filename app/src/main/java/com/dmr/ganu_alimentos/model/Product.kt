package com.dmr.ganu_alimentos.model

data class Product(
            val title:String? = null,
            val photoUrl: String? = null,
            val price:Double?,
            val sale:Boolean?) {
    constructor() : this("ProductoG","https://i.ibb.co/ftntGsL/ganu-prod-image-1-min.jpg",0.0, true)
}