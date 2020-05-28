package com.dmr.ganu_alimentos.model

class CartItem(
    var id: String? = null,
    var products: Product?,
    var quantity: Int?
) {

    constructor() : this("", null,0)
}