package io.forus.me.helpers

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import io.forus.me.entities.base.EthereumItem

/**
 * Created by martijn.doornik on 27/03/2018.
 */
abstract class LiveDataAdapter<T : EthereumItem, VH : RecyclerView.ViewHolder>
(lifecycleOwner: LifecycleOwner, data: LiveData<List<T>>): RecyclerView.Adapter<VH>() {
    protected var items: List<T> = emptyList()
    override fun getItemCount(): Int {
        return items.size
    }

    init {
        data.observe(lifecycleOwner, Observer {
            newItems: List<T>? ->
            run {
                if (newItems != null) {
                    items = newItems
                    this.notifyDataSetChanged()
                }
            }
        })
    }
}