package com.hackwithsodiq.movemate.model

data class ItemCategory(
    var selected: Boolean,
    val name: String
){
    companion object {
        val categoryItems = listOf("Documents", "Glass", "Liquid", "Food", "Electronic", "Product", "Other")
    }
}
