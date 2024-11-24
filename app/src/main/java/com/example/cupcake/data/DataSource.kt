package com.example.cupcake.data

import com.example.cupcake.R

object DataSource {
    //mengambil daftar rasa
    val flavors = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )
    //mengambil opsi kuantitas 1,6,12 cupcake
    val quantityOptions = listOf(
        Pair(R.string.one_cupcake, 1),
        Pair(R.string.six_cupcakes, 6),
        Pair(R.string.twelve_cupcakes, 12)
    )
}
