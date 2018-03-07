package io.forus.me.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.forus.me.entities.Asset

/**
 * Created by martijn.doornik on 05/03/2018.
 */

//@Dao
interface AssetDao {

    @Query("SELECT * FROM `asset`")
    fun getAssets(): LiveData<List<Asset>>

    @Insert
    fun create(asset: Asset)

    @Delete
    fun delete(asset: Asset)
}