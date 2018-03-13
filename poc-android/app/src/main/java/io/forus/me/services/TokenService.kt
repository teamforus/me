package io.forus.me.services

import android.arch.lifecycle.LiveData
import io.forus.me.entities.Token
import io.forus.me.services.base.BaseService

/**
 * Created by martijn.doornik on 27/02/2018.
 */
class TokenService : BaseService() {


    companion object {
        val inject: TokenService = TokenService()

        fun addToken(token: Token) {
            DatabaseService.inject.insert(token)
        }

        fun getTokensByAccount(account:String): LiveData<List<Token>>? {
            return DatabaseService.database?.tokenDao()?.getTokens()
        }
    }
}