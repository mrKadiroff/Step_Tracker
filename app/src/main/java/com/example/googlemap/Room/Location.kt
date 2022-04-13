package com.example.googlemap.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Location {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null


    var Latitude: Double? = null

    var Longitude: Double? = null


    constructor(Latitude: Double?, Longitude: Double?) {
        this.Latitude = Latitude
        this.Longitude = Longitude
    }
}
