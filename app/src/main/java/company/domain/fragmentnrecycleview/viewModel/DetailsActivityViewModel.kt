package company.domain.fragmentnrecycleview.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import company.domain.fragmentnrecycleview.network.model.WeatherResponse
import company.domain.fragmentnrecycleview.repository.DetailsActivityRepository

class DetailsActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DetailsActivityRepository(application)
    var showProgress: LiveData<Boolean>
    var response: LiveData<WeatherResponse>

    init {
        showProgress = repository.showProgress
        response = repository.response

    }

    fun getWeather(woeId: Int) {
        repository.getWeather(woeId)

    }
}