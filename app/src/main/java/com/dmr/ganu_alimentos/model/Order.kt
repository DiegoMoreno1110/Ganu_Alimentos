package com.dmr.ganu_alimentos.model

class Order(
    var id : String? = null,
    var cartItem : CartItem? ,
    var total : Double?
) {

    constructor() :  this("", null, 0.0)

}