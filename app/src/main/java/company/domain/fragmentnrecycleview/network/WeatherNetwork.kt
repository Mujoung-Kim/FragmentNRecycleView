package company.domain.fragmentnrecycleview.network

import company.domain.fragmentnrecycleview.network.model.LocationItem
import company.domain.fragmentnrecycleview.network.model.WeatherResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://www.metaweather.com/api/location/"

interface WeatherNetwork {
    //  https://www.metaweather.com/api/location/search?query=ban

    @GET("search?")
    fun getLocation(@Query("query")searchString: String): Call<List<LocationItem>>

    @GET("{woeid}")
    fun getWeather(@Path("woeid") woeid: Int): Call<WeatherResponse>

}