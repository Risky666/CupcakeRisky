package com.example.cupcake.data


data class OrderUiState(
    /** memilih kuantitas cupcake (1, 6, 12) */
    val quantity: Int = 0,
    /** rasa cupcake "Chocolate", "Vanilla", dll...*/
    val flavor: String = "",
    /** memilih tanggal pesanan cupcake */
    val date: String = "",
    /** Totalharga cupcake yang di pesan */
    val price: String = "",
    /** tanggal tersedianya cupcake*/
    val pickupOptions: List<String> = listOf()
)
