package company.domain.fragmentnrecycleview.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import company.domain.fragmentnrecycleview.network.model.LocationItem
import company.domain.fragmentnrecycleview.repository.SearchActivityRepository

class SearchActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = SearchActivityRepository(application)
    val showProgress: LiveData<Boolean>
    val locationList: LiveData<List<LocationItem>>

    init {
        this.showProgress = repository.showProgress
        this.locationList = repository.locationList

    }

    fun changeState() {
        repository.changeState()

    }

    fun searchLocation(searchString: String) {
        repository.searchLocation(searchString)

    }

    fun getWeather(woeId: Int) {
        repository.getWeather(woeId)

    }
}