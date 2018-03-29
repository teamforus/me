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

@Dao
interface AssetDao {

    @Query("SELECT * FROM `asset` WHERE `identity` = :arg0")
    fun getAssets(identity:String): LiveData<List<Asset>>

    @Query("SELECT * FROM `asset` WHERE `address` = :arg0 AND `identity` = :arg1")
    fun getAssetByAddressByIdentity(address:String, identity: String): Asset

    @Insert
    fun insert(asset: Asset)

    @Delete
    fun delete(asset: Asset)
}