//OrderViewModel digunakan untuk menyimpan dan mengelola informasi
// tentang pesanan cupcake, seperti jumlah, rasa, dan tanggal pengambilan.
// Selain itu, OrderViewModel juga bertanggung jawab untuk menghitung total harga pesanan berdasarkan detail pesanan tersebut.

package com.example.cupcake.ui
import androidx.lifecycle.ViewModel
import com.example.cupcake.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

//Menentukan harga per cupcake sebagai nilai tetap ($2.00).
private const val PRICE_PER_CUPCAKE = 2.00

//Menentukan biaya tambahan untuk pengambilan di hari yang sama ($3.00).
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

//Membuat kelas OrderViewModel yang mewarisi ViewModel, untuk mengelola data pesanan cupcake
class OrderViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(OrderUiState(pickupOptions = pickupOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()


    //Mengubah status _uiState menggunakan fungsi update.
    //Mengatur jumlah cupcake (quantity) dan memperbarui harga (price).
    fun setQuantity(numberCupcakes: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = numberCupcakes,
                price = calculatePrice(quantity = numberCupcakes)
            )
        }
    }

    //Mengatur rasa cupcake. Fungsi ini memperbarui nilai flavor dalam state.
    fun setFlavor(desiredFlavor: String) {
        _uiState.update { currentState ->
            currentState.copy(flavor = desiredFlavor)
        }
    }

    //Mengatur tanggal pengambilan pesanan. Jika tanggalnya hari ini, harga akan ditambahkan biaya tambahan (PRICE_FOR_SAME_DAY_PICKUP).
    fun setDate(pickupDate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = pickupDate,
                price = calculatePrice(pickupDate = pickupDate)
            )
        }
    }

    //Mereset semua data pesanan ke nilai awal.
    fun resetOrder() {
        _uiState.value = OrderUiState(pickupOptions = pickupOptions())
    }


    //Menghitung harga total berdasarkan jumlah (quantity) dan tanggal pengambilan (pickupDate).
    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatedPrice = quantity * PRICE_PER_CUPCAKE
        // Menambahkan biaya tambahan jika pengambilan dilakukan di hari yang sama.
        if (pickupOptions()[0] == pickupDate) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        //Mengembalikan hasil perhitungan dalam format mata uang.
        val formattedPrice = NumberFormat.getCurrencyInstance().format(calculatedPrice)
        return formattedPrice
    }


    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // Membuat daftar opsi tanggal mulai dari hari ini hingga 3 hari berikutnya.
        repeat(4) {
            //Menggunakan SimpleDateFormat untuk memformat tanggal sesuai dengan lokal perangkat.
            dateOptions.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return dateOptions
    }
}
