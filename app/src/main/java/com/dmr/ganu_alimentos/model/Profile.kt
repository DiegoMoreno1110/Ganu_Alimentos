package com.dmr.ganu_alimentos.model


class Profile(
    var id: String? = null,
    var address: String? = null,
    var email: String? = null,
    var password: String? = null,
    var card: PayInfomation?
){

    constructor() : this("","","","",null)



}