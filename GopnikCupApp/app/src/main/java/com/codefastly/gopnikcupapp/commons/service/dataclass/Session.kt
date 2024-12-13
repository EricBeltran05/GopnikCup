package com.codefastly.gopnikcupapp.commons.service.dataclass

data class Session(
    val id: String,
    val name: String,
    val status: String,
    val players: List<Player>
)