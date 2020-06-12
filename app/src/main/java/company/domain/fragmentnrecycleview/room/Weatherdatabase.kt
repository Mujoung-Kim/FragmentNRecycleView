package company.domain.fragmentnrecycleview.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import company.domain.fragmentnrecycleview.network.model.ConsolidatedWeather
import company.domain.fragmentnrecycleview.network.model.LocationItem

@Database(entities = [LocationItem::class, ConsolidatedWeather::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    companion object {
        fun get(application: Application): WeatherDatabase {
            return Room.databaseBuilder(application, WeatherDatabase::class.java, "weather.db")
                .build()

        }
    }

    abstract fun getWeatherDao(): WeatherDao

}