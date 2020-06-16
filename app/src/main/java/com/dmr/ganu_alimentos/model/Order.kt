package com.dmr.ganu_alimentos.model

class Order(
    var id : String? = null,
    var cartItem : String? ,
    var total : Double?
) {

    constructor() :  this("", null, 0.0)

}