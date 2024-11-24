package com.example.cupcake.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.R
import com.example.cupcake.ui.components.FormattedPriceLabel
import com.example.cupcake.ui.theme.CupcakeTheme

/**
 * Composable that displays the list of items as [RadioButton] options,
 * [onSelectionChanged] lambda that notifies the parent composable when a new value is selected,
 * [onCancelButtonClicked] lambda that cancels the order when user clicks cancel and
 * [onNextButtonClicked] lambda that triggers the navigation to next screen
 */
@Composable
//ini adalah fungsi Composable yang membuat layar untuk
// memilih opsi dari daftar item menggunakan RadioButton.
fun SelectOptionScreen(
    subtotal: String,//Harga subtotal yang ditampilkan di bawah daftar.
    options: List<String>,//Daftar string untuk opsi yang akan ditampilkan sebagai tombol radio.
    onSelectionChanged: (String) -> Unit = {},//Lambda yang dipanggil saat opsi dipilih.
    onCancelButtonClicked: () -> Unit = {},//Lambda yang dipanggil saat tombol Cancel diklik.
    onNextButtonClicked: () -> Unit = {},//Lambda yang dipanggil saat tombol Next diklik.
    modifier: Modifier = Modifier //Modifier opsional untuk mengatur tata letak Column.
) {

    //State yang digunakan untuk melacak nilai opsi yang dipilih.
    var selectedValue by rememberSaveable { mutableStateOf("") }



    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween //Membagi ruang antara elemen pertama (daftar opsi) dan elemen terakhir (tombol).
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            //Mengiterasi setiap elemen dalam daftar options untuk membuat RadioButton dan teks deskriptif.
            options.forEach { item ->
                //Mengatur tata letak horizontal untuk tombol radio dan teks.
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Komponen yang menunjukkan status "dipilih/tidak dipilih".
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
            //Menambahkan garis horizontal sebagai pembatas antara daftar opsi dan subtotal.
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
            //Menampilkan subtotal dengan format harga
            FormattedPriceLabel(
                subtotal = subtotal,
                modifier = Modifier
                    .align(Alignment.End)//Menempatkan subtotal di sisi kanan layar.
                    .padding(
                        top = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
            )
        }

        //Mengatur tata letak horizontal untuk tombol Cancel dan Next.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),//horizontalArrangement = Arrangement.spacedBy: Menambahkan spasi antara tombol.
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }

}

@Preview
@Composable
fun SelectOptionPreview() {
    CupcakeTheme {
        SelectOptionScreen(
            subtotal = "299.99",
            options = listOf("Option 1", "Option 2", "Option 3", "Option 4"),
            modifier = Modifier.fillMaxHeight()
        )
    }
}
