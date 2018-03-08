package io.forus.me.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.forus.me.entities.Service

/**
 * Created by martijn.doornik on 05/03/2018.
 */

//@Dao
interface ServiceDao {

    @Query("SELECT * FROM `asset`")
    fun getServices(): LiveData<List<Service>>

    @Insert
    fun create(service: Service)

    @Delete
    fun delete(service: Service)
}