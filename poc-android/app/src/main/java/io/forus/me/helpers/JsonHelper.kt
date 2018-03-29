package io.forus.me.helpers

import io.forus.me.entities.Asset
import io.forus.me.entities.Record
import io.forus.me.entities.Service
import io.forus.me.entities.Token
import io.forus.me.entities.base.EthereumItem
import org.json.JSONObject

/**
 * Created by martijn.doornik on 06/03/2018.
 */
class JsonHelper {
    class Keys {
        companion object {
            val ADDRESS: String = "address"
            val ASSET: String = "asset"
            val NAME: String = "name"
            val RECORD: String = "record"
            val SERVICE: String = "service"
            val TOKEN: String = "token"
            val TYPE: String = "type"
        }

    }

    companion object {

        fun toEthereumItem(dataString: String): EthereumItem? {
            try {
                val jsonObject = JSONObject(dataString)
                val address: String = jsonObject.getString(Keys.ADDRESS)
                val name: String = jsonObject.getString(Keys.NAME)
                val type: String = jsonObject.getString(Keys.TYPE)
                when (type) {
                    Keys.ASSET -> {
                        return Asset(address, name)
                    }
                    Keys.RECORD -> {
                        return Record(address, name)
                    }
                    Keys.SERVICE -> {
                        return Service(address, name)
                    }
                    Keys.TOKEN -> {
                        return Token(address, name)
                    }
                }
            } catch (e: Exception) {}
            return null
        }
    }

    class InvalidJsonException(val jsonString:String): Exception()
}