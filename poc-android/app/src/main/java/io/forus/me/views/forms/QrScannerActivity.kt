package io.forus.me.views.forms
/*
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.google.zxing.Result*/
//import me.dm7.barcodescanner.zxing.ZXingScannerView


class QrScannerActivity// : Activity(), ZXingScannerView.ResultHandler {
{
// TODO Better one :3
    val BARCODE: String = "BarCode"
/*
    private lateinit var mScannerView: ZXingScannerView

    override fun handleResult(result: Result?) {
        if (result != null) {
            val code: String = result.text
            val intent = Intent()
            intent.putExtra(BARCODE, code)
            setResult(ReturnCode.SUCCESS)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mScannerView = ZXingScannerView(this)
        setContentView(this.mScannerView)

        mScannerView.startCamera()
        mScannerView.setAutoFocus(true)
        mScannerView.setResultHandler(this)
        Log.e("Hello, this is dog", this.mScannerView.width.toString())

    }

    override fun onResume() {
        super.onResume()
        mScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }

    class ReturnCode {
        companion object {
            val CANCEL: Int = 4
            val FAILURE: Int = 3
            val SUCCESS: Int = 2
        }
    }*/
}