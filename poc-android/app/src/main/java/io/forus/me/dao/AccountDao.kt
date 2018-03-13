package io.forus.me.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.forus.me.entities.Account

/**
 * Created by martijn.doornik on 05/03/2018.
 */

@Dao
interface AccountDao {

    @Query("SELECT * FROM `account` WHERE `id` = :arg0")
    fun getAccountById(accountId: Int): Account

    @Query("SELECT * FROM `account`")
    fun getAccounts(): LiveData<List<Account>>

    @Insert
    fun create(account: Account)

    @Delete
    fun delete(account: Account)
}