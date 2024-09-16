package com.hackwithsodiq.movemate.model

data class ItemOrder (
    var itemName: String,
    var orderId: String,
    var startDestination: String,
    var ensDestination: String
) {
    companion object {
        fun samples() = listOf(ItemOrder("Macbook pro M2", "#NE43857340857904", "Paris", "Morocco"),
            ItemOrder("Summer linen jacket", "#NEJ20089934122231", "Barcelona", "Paris"),
            ItemOrder("Tapered-fit jeans AW", "#NEJ35870264978659", "Colombia", "Paris"),
            ItemOrder("Slim fit jeans AW", "#NEJ35870264912349", "Bogota", "Dhaka"),
            ItemOrder("Office setup desk", "#NEJ23481570754963", "France", "German"))

        fun getFilttedItems(query: String): List<ItemOrder> {
            return if (query.isEmpty()) samples() else samples().filter { it.orderId.startsWith(query, ignoreCase = true) }
        }
    }
}