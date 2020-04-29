package com.frogobox.recycler.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frogobox.recycler.R
import com.frogobox.recycler.base.views.FrogoRecyclerViewAdapterInterface

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * FrogoRecyclerViewAdapter
 * Copyright (C) 20/12/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoviewadapter
 *
 */
abstract class FrogoRecyclerViewAdapter<T> : RecyclerView.Adapter<FrogoRecyclerViewHolder<T>>(),
    FrogoRecyclerViewAdapterInterface<T> {

    private val mRecyclerViewDataList = mutableListOf<T>()

    private var mViewListener: FrogoRecyclerViewListener<T>? = null
    private var mRecyclerViewLayout: Int = 0

    private var mCustomViewInt: Int = 0
    private var mEmptyViewInt: Int = R.layout.frogo_rv_empty_view

    private lateinit var mCustomView: View
    private lateinit var mEmptyView: View

    private var hasEmptyView = false
    private var listCount = 0

    private fun layoutHandle() {
        mRecyclerViewLayout = if (mRecyclerViewDataList.isNotEmpty()) {
            mCustomViewInt
        } else {
            mEmptyViewInt
        }
    }

    private fun emptyViewHandle() {
        layoutHandle()
        notifyDataSetChanged()
    }

    override fun setupRequirement(
        layoutItem: Int,
        dataList: List<T>?,
        viewListener: FrogoRecyclerViewListener<T>?
    ) {

        if (viewListener != null) {
            mViewListener = viewListener
        }

        mRecyclerViewDataList.clear()
        if (dataList != null) {
            mRecyclerViewDataList.addAll(dataList)
        }

        mCustomViewInt = layoutItem
        emptyViewHandle()
    }

    override fun setupEmptyView(emptyView: Int?) {
        hasEmptyView = true
        if (emptyView != null) {
            mEmptyViewInt = emptyView
        }
        emptyViewHandle()
    }

    override fun viewLayout(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(mRecyclerViewLayout, parent, false)
    }

    override fun getItemCount(): Int {
        return if (hasEmptyView) {
            listCount = if (mRecyclerViewDataList.size == 0) {
                1
            } else {
                mRecyclerViewDataList.size
            }
            listCount
        } else {
            mRecyclerViewDataList.size
        }
    }

    override fun onBindViewHolder(holder: FrogoRecyclerViewHolder<T>, position: Int) {
        if (hasEmptyView) {
            if (mRecyclerViewDataList.size != 0) {
                holder.bindItem(mRecyclerViewDataList[position], mViewListener)
            }
        } else {
            holder.bindItem(mRecyclerViewDataList[position], mViewListener)
        }
    }

}