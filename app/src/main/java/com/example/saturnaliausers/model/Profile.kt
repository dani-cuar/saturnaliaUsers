package com.example.saturnaliausers.model

data class Profile(
    var uid: String? = null,
    var name: String? = null,
    var email: String? = null,
    //var rating: Float? = null,
    var about: String? = null,
    var address: String? = null,
    var phone: String? = null,
)
