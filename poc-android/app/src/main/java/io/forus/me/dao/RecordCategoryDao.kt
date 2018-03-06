package io.forus.me.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.forus.me.entities.RecordCategory

/**
 * Created by martijn.doornik on 28/02/2018.
 */
@Dao
interface RecordCategoryDao {
    @Query("SELECT * FROM recordCategory WHERE `account` = :arg0")
    fun getCategoriesFromAccount(address: String): LiveData<List<RecordCategory>>

    @Insert
    fun insert(recordCategory: RecordCategory)

    @Delete
    fun delete(recordCategory: RecordCategory)
}