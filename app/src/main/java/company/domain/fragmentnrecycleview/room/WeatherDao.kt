package company.domain.fragmentnrecycleview.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import company.domain.fragmentnrecycleview.network.model.LocationItem

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(list :List<LocationItem>)

    @Query("select * from locationitem where title like :search")
    fun getLocation(search: String): List<LocationItem>

}