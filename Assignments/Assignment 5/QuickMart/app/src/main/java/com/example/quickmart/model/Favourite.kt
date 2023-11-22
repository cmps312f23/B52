package com.example.quickmart.model

import java.util.UUID

class Favourite(
    var productId: String,
    var id: String = UUID.randomUUID().toString(),
    var userId: String = UUID.randomUUID().toString()
)