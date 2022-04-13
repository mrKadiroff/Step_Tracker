package com.example.googlemap.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LocationDao {

    @Insert
    fun insertLocation(location: Location)

    @Query("select * from location")
    fun getAllLocation(): List<Location>
}