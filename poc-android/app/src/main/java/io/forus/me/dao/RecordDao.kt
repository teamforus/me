package io.forus.me.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.forus.me.entities.Record
import io.forus.me.entities.RecordCategory

/**
 * Created by martijn.doornik on 28/02/2018.
 */
@Dao
interface RecordDao {
    @Query("SELECT * FROM record WHERE `recordCategoryId` = :arg0")
    fun getRecordsFromCategory(category: Int): LiveData<List<Record>>

    @Insert
    fun insert(recordCategory: Record)

    @Delete
    fun delete(recordCategory: Record)
}