package com.example.chat002

data class User (
    var name: String,
    var email: String,
    var uld: String
) {
   constructor(): this("","","")
}