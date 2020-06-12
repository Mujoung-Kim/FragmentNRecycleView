package company.domain.fragmentnrecycleview.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

import company.domain.fragmentnrecycleview.network.BASE_URL
import company.domain.fragmentnrecycleview.network.WeatherNetwork
import company.domain.fragmentnrecycleview.network.model.WeatherResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivityRepository(val application: Application) {
    val showProgress = MutableLiveData<Boolean>()
    val response = MutableLiveData<WeatherResponse>()
    var lastRequestTime: Long = -1

    fun getWeather(woeId: Int) {
        if (System.currentTimeMillis() - lastRequestTime > 10000) return

        showProgress.value = true

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(WeatherNetwork::class.java)

        service.getWeather(woeId).enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, "Error wile accessing the API", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<WeatherResponse>, resp: Response<WeatherResponse>) {
                lastRequestTime = System.currentTimeMillis()
                response.value = resp.body()
                showProgress.value = false

            }
        })
    }
}