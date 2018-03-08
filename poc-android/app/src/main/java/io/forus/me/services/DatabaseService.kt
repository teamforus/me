package io.forus.me.services

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import io.forus.me.dao.AccountDao
import io.forus.me.dao.RecordCategoryDao
import io.forus.me.dao.RecordDao
import io.forus.me.dao.TokenDao
import io.forus.me.entities.Account
import io.forus.me.entities.Record
import io.forus.me.entities.RecordCategory
import io.forus.me.entities.Token
import kotlin.concurrent.thread

@Database(entities = arrayOf(
        Account::class,
        Record::class,
        RecordCategory::class,
        Token::class
        ), version = 3)
abstract class DatabaseService: RoomDatabase() {
    private val ACCOUNT_THREAD: String = "DATA_ACCOUNT"
    private val RECORD_THREAD: String = "DATA_RECORD"
    private val TOKEN_THREAD: String = "DATA_TOKEN"

    private var threads: HashMap<String, DataThread> = HashMap()

    private val accountThread: DataThread
            get() {
                if (!threads.containsKey(ACCOUNT_THREAD)) {
                    threads[ACCOUNT_THREAD] = DataThread(ACCOUNT_THREAD)
                    threads[ACCOUNT_THREAD]!!.start()
                }
                return threads[ACCOUNT_THREAD]!!
            }

    private val recordThread: DataThread
            get() {
                if (!threads.containsKey(RECORD_THREAD)) {
                    threads[RECORD_THREAD] = DataThread(RECORD_THREAD)
                    threads[RECORD_THREAD]!!.start()
                }
                return threads[RECORD_THREAD]!!
            }
    private val tokenThread: DataThread
        get() {
            if (!threads.containsKey(TOKEN_THREAD)) {
                threads[TOKEN_THREAD] = DataThread(TOKEN_THREAD)
                threads[TOKEN_THREAD]!!.start()
            }
            return threads[TOKEN_THREAD]!!
        }

    abstract fun accountDao():AccountDao
    abstract fun recordDao(): RecordDao
    abstract fun recordCategoryDao(): RecordCategoryDao
    abstract fun tokenDao(): TokenDao

    fun delete(account: Account) {
        accountThread.postTask(Runnable { accountDao().delete(account) })
    }

    fun delete(record: Record) {
        recordThread.postTask(Runnable { recordDao().delete(record) })
    }

    fun delete(recordCategory: RecordCategory) {
        recordThread.postTask(Runnable { recordCategoryDao().delete(recordCategory) })
    }

    fun delete(token: Token) {
        tokenThread.postTask(Runnable { tokenDao().delete(token) })
    }

    fun insert(account: Account) {
        accountThread.postTask(Runnable { accountDao().create(account) })
    }

    fun insert(record: Record) {
        recordThread.postTask(Runnable {recordDao().insert(record)})
    }

    fun insert(recordCategory: RecordCategory) {
        recordThread.postTask(Runnable { recordCategoryDao().insert(recordCategory) })
    }

    fun insert(token: Token) {
        tokenThread.postTask(Runnable { tokenDao().insert(token) })
    }

    companion object {
        var database: DatabaseService? = null
        val ready:Boolean
            get() = database != null

        fun inject(context: Context): DatabaseService {
            if (database == null) {
                synchronized(DatabaseService::class) {
                    database = Room.databaseBuilder(context.applicationContext,
                            DatabaseService::class.java, "me_client_architecture")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return database!!
        }

        val inject: DatabaseService
                get() {
                    if (database == null) {
                        throw RuntimeException("Database not yet initiated")
                    }
                    return database!!
                }

    }

    class DataThread(name: String): HandlerThread(name) {
        private lateinit var mHandler: Handler
        private var loaded: Boolean = false

        override fun onLooperPrepared() {
            super.onLooperPrepared()
            mHandler = Handler(looper)
            loaded = true
        }

        fun postTask(task: Runnable) {
            while (!loaded) {}
            mHandler.post(task)
        }
    }
}