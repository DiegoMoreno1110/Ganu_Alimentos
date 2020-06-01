package com.dmr.ganu_alimentos.model

class PayInfomation(
    var id: String? = null,
    var nameCard: String? = null,
    var numberCard: Int?,
    var cvc: Int?,
    var expirationDate: String?
) {

    constructor() : this("","", 0, 0, "")

}