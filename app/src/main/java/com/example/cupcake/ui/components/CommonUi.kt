package com.example.cupcake.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cupcake.R


//Kode ini mendefinisikan sebuah fungsi Composable bernama FormattedPriceLabel. Fungsi ini digunakan untuk menampilkan harga yang ditampilkan di layar.

@Composable
//Nama fungsi yang akan Anda panggil untuk menampilkan label harga.
fun FormattedPriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    Text(
        //Fungsi ini mengambil string dari resource strings.xml dengan ID R.string.subtotal_price.
        text = stringResource(R.string.subtotal_price, subtotal),
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}
