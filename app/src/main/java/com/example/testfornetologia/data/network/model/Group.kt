package com.example.testfornetologia.data.network.model

data class Group(
    val badge: Badge,
    val id: String,
    val items: List<Item>,
    val link: String,
    val title: String
)