package com.dmr.ganu_alimentos.model

data class Product(
            var title:String? = null,
            var photoUrl: String? = null,
            var price:Double?,
            var sale:Boolean?) {
    constructor() : this("ProductoG","https://i.ibb.co/ftntGsL/ganu-prod-image-1-min.jpg",0.0, true)
}