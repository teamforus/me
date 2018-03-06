package io.forus.me.entities

import android.arch.persistence.room.*

@Entity
class RecordCategory (

        var label: String = "",

        var account: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}