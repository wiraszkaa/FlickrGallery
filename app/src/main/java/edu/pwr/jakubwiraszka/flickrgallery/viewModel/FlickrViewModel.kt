package edu.pwr.jakubwiraszka.flickrgallery.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.pwr.jakubwiraszka.flickrgallery.config.FlickrConfig
import edu.pwr.jakubwiraszka.flickrgallery.model.FlickrResponse
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class FlickrViewModel : ViewModel() {
    var flickrData = mutableStateOf<FlickrResponse?>(null)
    var isLoading = mutableStateOf(false)
    var isError = mutableStateOf(false)
    var errorMessage: String = ""
        private set

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            isLoading.value = true
            isError.value = false

            val client = FlickrConfig.getApiService().getFlickrResponse()

            client.enqueue(object : Callback<FlickrResponse> {

                override fun onResponse(
                    call: Call<FlickrResponse>,
                    response: Response<FlickrResponse>
                ) {
                    val responseBody = response.body()
                    if (!response.isSuccessful || responseBody == null) {
                        onError("Data Processing Error")
                        return
                    }

                    isLoading.value = false
                    flickrData.value = responseBody
                }

                override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
                    onError(t.message)
                    t.printStackTrace()
                }

            })
        }
    }

    private fun onError(inputMessage: String?) {
        val message =
            if (inputMessage.isNullOrBlank() or inputMessage.isNullOrEmpty()) "Unknown Error"
            else inputMessage

        errorMessage = StringBuilder("ERROR: ")
            .append("$message some data may not displayed properly").toString()

        isError.value = true
        isLoading.value = false
    }
}