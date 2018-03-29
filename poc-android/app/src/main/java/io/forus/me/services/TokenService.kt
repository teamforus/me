package io.forus.me.services

import android.arch.lifecycle.LiveData
import io.forus.me.entities.Token
import io.forus.me.services.base.BaseService

/**
 * Created by martijn.doornik on 27/02/2018.
 */
class TokenService : BaseService() {


    companion object {
        fun addToken(token: Token) {
            DatabaseService.inject.insert(token)
        }

        fun getTokensByIdentity(identity:String): LiveData<List<Token>>? {
            return DatabaseService.database?.tokenDao()?.getTokens(identity)
        }

        fun getTokenByAddressByIdentity(address:String, identity:String): Token? {
            return DatabaseService.database?.tokenDao()?.getTokenByAddressByIdentity(address, identity)
        }
    }
}