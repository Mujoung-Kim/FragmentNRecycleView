package company.domain.fragmentnrecycleview.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationItem(
    val latt_long: String,
    val location_type: String,
    val title: String,
    @PrimaryKey
    val woeid: Int
)