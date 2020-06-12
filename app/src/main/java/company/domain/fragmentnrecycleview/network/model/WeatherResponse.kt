package company.domain.fragmentnrecycleview.network.model

data class WeatherResponse(
    val consolidated_weather: List<ConsolidatedWeather>,
    val time: String,
    val title: String,
    val woeid: Int
)