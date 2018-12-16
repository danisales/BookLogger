package br.ufpe.cin.dso.booklogger.service

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import br.ufpe.cin.dso.booklogger.util.GoogleBooksRequest
import java.io.Serializable


class GoogleBooksService : IntentService("GoogleBooksService") {
    private val TAG = "GoogleBooksService"

    override fun onHandleIntent(intent: Intent?) {
        var query = intent!!.extras.getString("QUERY")

        var items = GoogleBooksRequest().results(query)
        var list = ArrayList(items) as Serializable

        var bundle = Bundle()
        bundle.putSerializable("RESULT", list)

        var broadcast = Intent("GoogleBooksBroadcast")
        broadcast.putExtras(bundle)
        var instance = LocalBroadcastManager.getInstance(this)
                .sendBroadcast(broadcast)
    }

}
