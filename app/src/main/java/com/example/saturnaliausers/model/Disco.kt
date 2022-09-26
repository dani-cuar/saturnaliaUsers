package com.example.saturnaliausers.model

import java.io.Serializable

data class Disco(
    var uid: String? = null,
    var name: String? = null,
    var email: String? = null,
    var rating: String? = null,
    var about: String? = null,
    var address: String? = null,
    var phone: String? = null,
): Serializable
