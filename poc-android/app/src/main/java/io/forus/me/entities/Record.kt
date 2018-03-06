package io.forus.me.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import io.forus.me.entities.base.EthereumItem

/**
 * Created by martijn.doornik on 27/02/2018.
 */
@Entity(foreignKeys = arrayOf(ForeignKey(
        entity = RecordCategory::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("recordCategoryId"),
        onDelete = ForeignKey.CASCADE)))
class Record(
        address: String = "0x0",
        name: String = "",
        var recordCategoryId: Long? = null,
        @Ignore var value: String = "") : EthereumItem(address, name)