package company.domain.fragmentnrecycleview.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

import com.google.gson.Gson

import company.domain.fragmentnrecycleview.network.BASE_URL
import company.domain.fragmentnrecycleview.network.WeatherNetwork
import company.domain.fragmentnrecycleview.network.model.LocationItem
import company.domain.fragmentnrecycleview.network.model.WeatherResponse
import company.domain.fragmentnrecycleview.room.WeatherDatabase

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivityRepository(val application: Application) {
    val showProgress = MutableLiveData<Boolean>()
    val locationList = MutableLiveData<List<LocationItem>>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)

    }

    fun searchLocation(searchString: String) {
        showProgress.value = true

        //  Network call
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(WeatherNetwork::class.java)

        service.getLocation(searchString).enqueue(object : Callback<List<LocationItem>> {
            override fun onFailure(call: Call<List<LocationItem>>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, "Error wile accessing the API", Toast.LENGTH_SHORT)
                    .show()

            }

            override fun onResponse(
                call: Call<List<LocationItem>>,
                response: Response<List<LocationItem>>
            ) {
                Log.d("SearchRepository", "Response : ${Gson().toJson(response.body())}")
                locationList.value = response.body()
                if (response.body() != null)
                    InsertLocationData(response.body()!!, application).execute()
                showProgress.value = false

            }
        })
    }

    fun getWeather(woeId: Int) {
        showProgress.value = true

        //  Network call
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(WeatherNetwork::class.java)

        service.getWeather(23424960).enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, "Error wile accessing the API", Toast.LENGTH_SHORT)
                    .show()

            }

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                Log.d("SearchRepository", "Response : ${Gson().toJson(response.body())}")
                showProgress.value = false

            }
        })

    }

    class InsertLocationData(val list: List<LocationItem>, val application: Application) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void?): Void? {
            WeatherDatabase.get(application).getWeatherDao().insertLocation(list)
            WeatherDatabase.get(application).getWeatherDao().getLocation("%ban%").forEach {
                Log.d("SearchRepository", "location : ${it.title}")

            }
            return null

        }
    }
}