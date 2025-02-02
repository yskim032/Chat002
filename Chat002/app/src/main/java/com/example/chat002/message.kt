package com.example.chat002

data class Message(
    var message: String?,
    var sendId: String?

){
    constructor():this("","")
}