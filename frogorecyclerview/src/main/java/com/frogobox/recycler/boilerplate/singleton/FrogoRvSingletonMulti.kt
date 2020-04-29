package com.frogobox.recycler.boilerplate.singleton

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.recycler.FrogoRecyclerView
import com.frogobox.recycler.R
import com.frogobox.recycler.base.parent.FrogoRecyclerViewListener
import com.frogobox.recycler.boilerplate.adapter.callback.FrogoViewAdapterMultiCallback
import com.frogobox.recycler.boilerplate.viewmulti.FrogoViewAdapterMulti
import com.frogobox.recycler.boilerplate.viewmulti.FrogoViewHolderMultiCallback
import com.frogobox.recycler.util.FrogoRvConstant

/*
 * Created by Faisal Amir
 * =========================================
 * FrogoRecyclerViewAdapter
 * Copyright (C) 29/04/2020.      
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * FrogoBox Inc
 * com.frogobox.recycler.boilerplate.singleton
 * 
 */
class FrogoRvSingletonMulti<T> : FrogoRvSingletonMultiInterface<T> {

    private var emptyViewInt: Int = R.layout.frogo_rv_empty_view
    
    private lateinit var multiCustomView: List<Int>
    private lateinit var multiOptionHolder: List<Int>
    private lateinit var frogoViewAdapterMultiCallback: FrogoViewAdapterMultiCallback<T>
    private lateinit var frogoViewAdapterMulti: FrogoViewAdapterMulti<T>

    private lateinit var mFrogoRecyclerView: FrogoRecyclerView
    private var layoutSpanCount = 0
    private var optionLayoutManager = ""
    private var optionDividerItem = false
    private var listData: List<T>? = null
    private var optionAdapter = ""


    override fun initSingleton(frogoRecyclerView: FrogoRecyclerView): FrogoRvSingletonMulti<T> {
        mFrogoRecyclerView = frogoRecyclerView
        return this
    }

    override fun createLayoutLinearVertical(dividerItem: Boolean): FrogoRvSingletonMulti<T> {
        optionLayoutManager =
            FrogoRvConstant.LAYOUT_LINEAR_VERTICAL
        optionDividerItem = dividerItem
        Log.d("injector-layoutManager", optionLayoutManager)
        Log.d("injector-divider", optionDividerItem.toString())
        return this
    }

    override fun createLayoutLinearHorizontal(dividerItem: Boolean): FrogoRvSingletonMulti<T> {
        optionLayoutManager =
            FrogoRvConstant.LAYOUT_LINEAR_HORIZONTAL
        optionDividerItem = dividerItem
        Log.d("injector-layoutManager", optionLayoutManager)
        Log.d("injector-divider", optionDividerItem.toString())
        return this
    }

    override fun createLayoutStaggeredGrid(spanCount: Int): FrogoRvSingletonMulti<T> {
        optionLayoutManager =
            FrogoRvConstant.LAYOUT_STAGGERED_GRID
        layoutSpanCount = spanCount
        Log.d("injector-layoutManager", optionLayoutManager)
        Log.d("injector-divider", optionDividerItem.toString())
        return this
    }

    override fun createLayoutGrid(spanCount: Int): FrogoRvSingletonMulti<T> {
        optionLayoutManager =
            FrogoRvConstant.LAYOUT_GRID
        layoutSpanCount = spanCount
        Log.d("injector-layoutManager", optionLayoutManager)
        Log.d("injector-divider", optionDividerItem.toString())
        return this
    }

    override fun setupLayoutManager() {

        Log.d("injector-option", optionLayoutManager)
        Log.d("injector-divider", optionDividerItem.toString())
        Log.d("injector-spanCount", layoutSpanCount.toString())

        if (optionLayoutManager.equals(FrogoRvConstant.LAYOUT_LINEAR_VERTICAL)) {
            mFrogoRecyclerView.layoutManager =
                LinearLayoutManager(mFrogoRecyclerView.context, LinearLayoutManager.VERTICAL, false)
            if (optionDividerItem) {
                mFrogoRecyclerView.addItemDecoration(
                    DividerItemDecoration(
                        mFrogoRecyclerView.context,
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
        } else if (optionLayoutManager.equals(FrogoRvConstant.LAYOUT_LINEAR_HORIZONTAL)) {
            mFrogoRecyclerView.layoutManager = LinearLayoutManager(
                mFrogoRecyclerView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            if (optionDividerItem) {
                mFrogoRecyclerView.addItemDecoration(
                    DividerItemDecoration(
                        mFrogoRecyclerView.context,
                        LinearLayoutManager.HORIZONTAL
                    )
                )
            }
        } else if (optionLayoutManager.equals(FrogoRvConstant.LAYOUT_STAGGERED_GRID)) {
            mFrogoRecyclerView.layoutManager =
                StaggeredGridLayoutManager(layoutSpanCount, StaggeredGridLayoutManager.VERTICAL)
        } else if (optionLayoutManager.equals(FrogoRvConstant.LAYOUT_GRID)) {
            mFrogoRecyclerView.layoutManager =
                GridLayoutManager(mFrogoRecyclerView.context, layoutSpanCount)
        }

    }

    override fun addData(listData: List<T>): FrogoRvSingletonMulti<T> {
        this.listData = listData
        Log.d("injector-listData", this.listData.toString())
        return this
    }

    override fun addCustomView(listCustomViewInt: List<Int>): FrogoRvSingletonMulti<T> {
        multiCustomView = listCustomViewInt
        Log.d("injector-listLayout", this.multiCustomView.toString())
        return this
    }

    override fun addOptionHolder(listOptionHolder: List<Int>): FrogoRvSingletonMulti<T> {
        multiOptionHolder = listOptionHolder
        Log.d("injector-listOptHolder", this.multiOptionHolder.toString())
        return this
    }

    override fun addEmptyView(emptyViewInt: Int?): FrogoRvSingletonMulti<T> {
        if (emptyViewInt != null) this.emptyViewInt = emptyViewInt
        Log.d("injector-emptyView", this.emptyViewInt.toString())
        return this
    }

    override fun addCallback(frogoViewAdapterMultiCallback: FrogoViewAdapterMultiCallback<T>): FrogoRvSingletonMulti<T> {
        this.frogoViewAdapterMultiCallback = frogoViewAdapterMultiCallback
        Log.d("injector-adaptCallback", this.frogoViewAdapterMultiCallback.toString())
        return this
    }

    private fun createAdapter(): FrogoRvSingletonMulti<T> {
        optionAdapter = FrogoRvConstant.FROGO_ADAPTER_MULTI
        frogoViewAdapterMulti = FrogoViewAdapterMulti(object : FrogoViewHolderMultiCallback<T> {
            override fun setupInitComponent(view: View, data: T) {
                frogoViewAdapterMultiCallback.setupFirstInitComponent(view, data)
            }
        }, object : FrogoViewHolderMultiCallback<T> {
            override fun setupInitComponent(view: View, data: T) {
                frogoViewAdapterMultiCallback.setupSecondInitComponent(view, data)
            }
        })

        frogoViewAdapterMulti.setupRequirement(
            listData,
            multiCustomView,
            multiOptionHolder,
            object : FrogoRecyclerViewListener<T> {
                override fun onItemClicked(data: T) {
                    frogoViewAdapterMultiCallback.onFirstItemClicked(data)
                }

                override fun onItemLongClicked(data: T) {
                    frogoViewAdapterMultiCallback.onFirstItemLongClicked(data)
                }
            },
            object : FrogoRecyclerViewListener<T> {
                override fun onItemClicked(data: T) {
                    frogoViewAdapterMultiCallback.onSecondItemClicked(data)
                }

                override fun onItemLongClicked(data: T) {
                    frogoViewAdapterMultiCallback.onSecondItemLongClicked(data)
                }
            }
        )
        frogoViewAdapterMulti.setupEmptyView(emptyViewInt)

        return this
    }

    private fun setupInnerAdapter() {
        Log.d("injector-optionAdapter", optionAdapter)
        mFrogoRecyclerView.adapter = frogoViewAdapterMulti
    }

    override fun build(): FrogoRvSingletonMulti<T> {
        createAdapter()
        setupLayoutManager()
        setupInnerAdapter()
        return this
    }


}