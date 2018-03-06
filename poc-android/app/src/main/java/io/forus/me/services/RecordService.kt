package io.forus.me.services

import android.arch.lifecycle.LiveData
import io.forus.me.entities.Record
import io.forus.me.entities.RecordCategory
import io.forus.me.services.base.BaseService

/**
 * Created by martijn.doornik on 27/02/2018.
 */
class RecordService : BaseService() {

    fun getRecordCategoriesByAccount(account:String): LiveData<List<RecordCategory>>? {
        return DatabaseService.database?.recordCategoryDao()?.getCategoriesFromAccount(account)
    }

    companion object {
        val inject: RecordService = RecordService()

        /**
         * Create a new record to track.
         *
         * @param address the validated address
         * @param name the name of the record
         * @param category The category of the record
         */
        fun newRecord(address: String, name: String, category: RecordCategory) {
            DatabaseService.database?.insert(Record(address, name, category.id))
        }
    }
}