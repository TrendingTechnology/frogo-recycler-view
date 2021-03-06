package com.frogobox.recycler.boilerplate.viewmulti

import android.view.ViewGroup
import com.frogobox.recycler.base.viewmulti.FrogoRecyclerViewAdapterMulti
import com.frogobox.recycler.base.viewmulti.FrogoRecyclerViewHolderMulti
import com.frogobox.recycler.util.FrogoRvConstant.OPTION_HOLDER_FIRST
import com.frogobox.recycler.util.FrogoRvConstant.OPTION_HOLDER_SECOND

/*
 * Created by Faisal Amir
 * =========================================
 * FrogoRecyclerViewAdapter
 * Copyright (C) 26/04/2020.      
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * FrogoBox Inc
 * com.frogobox.recycler.boilerplate.adapter
 * 
 */
class FrogoViewAdapterMulti<T>(
    private val frogoViewHolderMultiCallbackFirst: FrogoViewHolderMultiCallback<T>,
    private val frogoViewHolderMultiCallbackSecond: FrogoViewHolderMultiCallback<T>
) :
    FrogoRecyclerViewAdapterMulti<T>() {

    private fun firstFrogoViewHolder(parent: ViewGroup): FrogoViewHolderFirst<T> {
        return FrogoViewHolderFirst(
            viewLayout(parent, OPTION_HOLDER_FIRST),
            frogoViewHolderMultiCallbackFirst
        )
    }

    private fun secondFrogoViewHolder(parent: ViewGroup): FrogoViewHolderSecond<T> {
        return FrogoViewHolderSecond(
            viewLayout(parent, OPTION_HOLDER_SECOND),
            frogoViewHolderMultiCallbackSecond
        )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FrogoRecyclerViewHolderMulti<T> {

        return when (viewType) {
            OPTION_HOLDER_FIRST -> {
                firstFrogoViewHolder(parent)
            }

            OPTION_HOLDER_SECOND -> {
                secondFrogoViewHolder(parent)
            }

            else -> {
                firstFrogoViewHolder(parent)
            }
        }

    }

}