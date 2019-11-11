package com.base.kotlin.core

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.base.kotlin.widget.interfaces.OnRecyclerViewItemClickListener
import java.util.*
import kotlin.collections.ArrayList

public abstract class BaseListAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private final val TAG: String ="BaseListAdapter"

    protected final val mList : ArrayList<T?> = ArrayList()

    // interface item click
    public interface onItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

    // interface long click
    public interface onItemLongClickListener{
        fun onItemLongClick(view: View, position: Int): Boolean
    }

    protected var mClickListener: onItemClickListener? = null
    protected var mLongClickListener: onItemLongClickListener? = null

    protected abstract fun createViewHolder(viewType: Int): IViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: IViewHolder<T> = createViewHolder(viewType)
        var view: View = viewHolder.createItemView(parent)
        return BaseViewHolder(view, viewHolder)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder !is BaseViewHolder<*>){
            throw IllegalAccessException("The ViewHolder item must extend BaseViewHolder")
        }

        val iHolder: IViewHolder<T> = holder.holder as IViewHolder<T>
        iHolder.onBind(getItem(position), position)

        holder.itemView.setOnClickListener{
            v: View->
            if(mClickListener !=null){
                mClickListener!!.onItemClick(v, position)
            }

            iHolder.onClick()
            onItemClick(v, position)
        }

        holder.itemView.setOnLongClickListener { v: View ->
            var isClicked = false
            if(mLongClickListener !=null){
                isClicked = mLongClickListener!!.onItemLongClick(v, position)
            }
            onItemLongClick(v, position)
            isClicked
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    public fun getItem(position: Int): T? {
        return mList.getOrNull(position)
    }

    protected fun onItemClick(v: View, position: Int){

    }

    protected fun onItemLongClick(v: View, position: Int){

    }

    public fun setOnItemClickListener (mListener: onItemClickListener){
        this.mClickListener = mListener
    }

    public fun setOnItemLongClickListener(mListener: onItemLongClickListener){
        this.mLongClickListener = mListener
    }

    public fun addItem(value: T){
        this.mList.add(value)
        notifyDataSetChanged()
    }

    public fun addItem(value: T, index: Int){
        this.mList.add(index, value)
        notifyDataSetChanged()
    }

    open fun addItems(values: List<T>){
        this.mList.addAll(values)
        notifyDataSetChanged()
    }

    public fun removeItem(value: T){
        this.mList.remove(value)
        notifyDataSetChanged()
    }

    public fun removeItems(values: List<T>){
        this.mList.removeAll(values)
        notifyDataSetChanged()
    }

    public fun getItems(): ArrayList<T>? {
        return Collections.unmodifiableList(mList) as ArrayList<T>
    }

    fun getItemSize(): Int {
        return mList.size
    }

    public fun clear(){
        this.mList.clear()
    }

    fun refreshItem(values: List<T>){
        this.mList.clear()
        this.mList.addAll(values)
        notifyDataSetChanged()
    }
}
