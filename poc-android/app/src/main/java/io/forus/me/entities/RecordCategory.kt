package io.forus.me.entities

import android.arch.lifecycle.LiveData

class RecordCategory (
        val id: Int,
        val label: String,
        val identity: String,
        val records: LiveData<List<Record>>?
)