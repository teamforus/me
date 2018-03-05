package io.forus.me.entities.base

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.graphics.Bitmap
import com.google.zxing.EncodeHintType
import net.glxn.qrgen.android.QRCode

/**
 * Created by martijn.doornik on 23/02/2018.
 */
@Entity
abstract class EthereumItem(
        @ColumnInfo(name="address")
        var address: String,

//        @ColumnInfo(name="name")
        var name: String) {

    @PrimaryKey
    var id: Long? = null

    val qrCode: Bitmap
        get() = QRCode.from(this.address).withHint(EncodeHintType.MARGIN, "0").bitmap()
}